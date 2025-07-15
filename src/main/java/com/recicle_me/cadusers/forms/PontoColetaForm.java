package com.recicle_me.cadusers.forms;

import com.recicle_me.cadusers.entities.DiasPontoColeta;
import com.recicle_me.cadusers.entities.TipoColeta;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PontoColetaForm {
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String status;
    private Integer userId;
    private TipoColeta tipoColeta;
    private DiasPontoColeta diasPontoColeta;
}
