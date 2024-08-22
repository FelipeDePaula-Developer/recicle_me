package com.healthcare.cadusers.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Entity
@Component
public class TipoColeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoColeta;

    @ManyToOne
    @JoinColumn(name = "idPontoColeta", referencedColumnName = "idPontoColeta")
    private PontoColeta pontoColeta;

    @Column(length = 3, name = "tipo_descarte")
    private String tipoDescarte;
}
