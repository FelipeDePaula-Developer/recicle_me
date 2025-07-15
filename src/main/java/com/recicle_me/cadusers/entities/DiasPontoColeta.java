package com.recicle_me.cadusers.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Entity
@Component
public class DiasPontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDiasPontoColeta;

    @ManyToOne
    @JoinColumn(name = "idPontoColeta", referencedColumnName = "idPontoColeta")
    private PontoColeta pontoColeta;

    @Column(length = 3, nullable = false)
    private String dayFlag;

    @Column(length = 4, nullable = false)
    private String openHour;

    @Column(length = 4, nullable = false)
    private String closeHour;

    @Column(length = 1, nullable = false)
    private String status = "T";
}
