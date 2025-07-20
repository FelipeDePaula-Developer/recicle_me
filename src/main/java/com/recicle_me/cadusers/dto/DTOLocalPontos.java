package com.recicle_me.cadusers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class DTOLocalPontos {
    private Integer idPontoColeta;
    private String nome;
    private String endereco;
    private ArrayList<String> geoLocalizacao;
}
