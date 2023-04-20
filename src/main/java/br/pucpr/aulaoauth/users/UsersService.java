package br.pucpr.aulaoauth.users;

import br.pucpr.aulaoauth.lib.apiDTOs.AccessCodeResponseDTO;
import br.pucpr.aulaoauth.lib.apiDTOs.UserLookupResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UsersService {
    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public void save(User user){
        usersRepository.save(user);
    }

    /**
     * retorna o nome de usuário do usuário autenticado no accessToken
     */
    public String retrieveUsername(String accessToken){
        var headers = new HttpHeaders();

        headers.add("Authorization",
                "Bearer " + accessToken);

        RestTemplate rt = new RestTemplate();
        var req  = new HttpEntity<>(headers);

        // get for object não suporta adição de headers
        var respBody = rt.exchange(
                "https://api.twitter.com/2/users/me?user.fields=created_at,description,entities,id",
                HttpMethod.GET,
                req,
                UserLookupResponseDTO.class).getBody();

        if(respBody == null){
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Failure to retrieve user");
        }

        return respBody.getData().getUsername();
    }
}
