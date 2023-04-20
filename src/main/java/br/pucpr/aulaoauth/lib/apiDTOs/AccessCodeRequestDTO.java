package br.pucpr.aulaoauth.lib.apiDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessCodeRequestDTO {
    private String code;
    private String grant_type;
    private String client_id;
    private String redirect_uri;
    private String code_verifier;

    // converter para um map para retornar como form
    public MultiValueMap<String, String> toMap(){
        var res =  new LinkedMultiValueMap<String, String>();
        res.add("code", code);
        res.add("grant_type", grant_type);
        res.add("client_id", client_id);
        res.add("redirect_uri", redirect_uri);
        res.add("code_verifier", code_verifier);

        return res;
    }
}
