package br.pucpr.aulaoauth.lib.apiDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLookupResponseDTO {
    UserObjectResponseDTO data;
}
