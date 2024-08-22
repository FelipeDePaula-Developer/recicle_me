package com.healthcare.cadusers.entities;

import com.healthcare.cadusers.entities.interfaces.Person;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
public class Client implements Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;

    @Column(length = 11)
    private String cpf;

    @Column
    private String email;

    @Column(name = "insert_timestamp", updatable = false)
    @CreationTimestamp
    private LocalDateTime insertTimestamp;

    @Column
    private String name;

    @Column(length = 1, nullable = false)
    private String status = "T";
}
