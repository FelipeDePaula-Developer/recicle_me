package com.recicle_me.cadusers.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Data
@Entity
@Component
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPontoColeta;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "idUser")
    private User user;

    @Column
    private String cep;

    @Column
    private String nome;

    @Column
    private String logradouro;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String estado;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @Column(length = 1, nullable = false)
    private String status = "T";
}
