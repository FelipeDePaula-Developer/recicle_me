package com.healthcare.cadusers.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@Entity
@Component
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPontoColeta;

    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private User user;

    @Column
    private String cep;

    @Column
    private String logradouro;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String estado;

    @Column(length = 1, nullable = false)
    private String status = "T";
}
