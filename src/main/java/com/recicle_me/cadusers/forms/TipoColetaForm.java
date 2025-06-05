package com.recicle_me.cadusers.forms;

import com.recicle_me.cadusers.entities.TipoColeta;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TipoColetaForm {
    private Integer tipoColetaId;
    private String tipoDescarte;
}
