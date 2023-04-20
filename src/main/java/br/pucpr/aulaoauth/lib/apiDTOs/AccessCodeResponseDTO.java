package br.pucpr.aulaoauth.lib.apiDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessCodeResponseDTO {
    private String token_type;
    private String expires_in;
    private String access_token;
    private String scope;
}
