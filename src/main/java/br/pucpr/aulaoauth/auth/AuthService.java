package br.pucpr.aulaoauth.auth;

import br.pucpr.aulaoauth.lib.apiDTOs.AccessCodeRequestDTO;
import br.pucpr.aulaoauth.lib.apiDTOs.AccessCodeResponseDTO;
import br.pucpr.aulaoauth.users.User;
import br.pucpr.aulaoauth.users.UsersService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    private final AuthSettings settings;
    private final UsersService usersService;

    public AuthService(AuthSettings settings, UsersService usersService) {
        this.settings = settings;
        this.usersService = usersService;
    }

    public void exchangeForAccessToken(String authorizationCode, String codeVerifier){
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        // de acordo com a documentação do twitter, o header "Authorization" precisa ser uma string
        // no formato "client-id:client-secret" e em base64
        headers.add("Authorization",
                "Basic " + Base64.encodeBase64String((settings.getClientId() + ":" + settings.getClientSecret()).getBytes()));

        RestTemplate rt = new RestTemplate();
        var req  = new HttpEntity<>(
                new AccessCodeRequestDTO(
                        authorizationCode,
                        "authorization_code",
                        settings.getClientId(),
                        "https://oauthdebugger.com/debug",
                        codeVerifier).toMap(),
                headers);

        var resp = rt.postForObject("https://api.twitter.com/2/oauth2/token",
                req,
                AccessCodeResponseDTO.class);

        if(resp == null){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Failure to retrieve user");
        }

        usersService.save(new User(null, "random", resp.getAccess_token()));

        System.out.println(usersService.retrieveUsername(resp.getAccess_token()));
    }
}
