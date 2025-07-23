package com.recicle_me.cadusers.forms;

import com.recicle_me.cadusers.entities.DiasPontoColeta;
import com.recicle_me.cadusers.entities.PhoneNumber;
import com.recicle_me.cadusers.entities.TipoColeta;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PontoColetaForm {
    private String cep;
    private String nome;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String status;
    private String latitude;
    private String longitude;
    private Integer userId;
    private List<TipoColeta> tipoColeta;
    private List<DiasPontoColeta> diasPontoColeta;
}
