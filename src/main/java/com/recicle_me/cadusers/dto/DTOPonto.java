package com.recicle_me.cadusers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class DTOPonto {
    private Integer idPontoColeta;
    private String nome;
    private String endereco;
    private List<String> tiposColeta;
    private Map<String, HorarioDTO> diasPontoColeta;

    @Data
    @AllArgsConstructor
    public static class HorarioDTO {
        private String open;
        private String close;
    }
}
