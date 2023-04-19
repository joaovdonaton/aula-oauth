package br.pucpr.aulaoauth.auth;

import br.pucpr.aulaoauth.auth.dto.AccessCodeRequestDTO;
import br.pucpr.aulaoauth.auth.dto.AccessCodeResponseDTO;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {
    private final AuthSettings settings;

    public AuthService(AuthSettings settings) {
        this.settings = settings;
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

        System.out.println(resp.getAccess_token());
    }
}
