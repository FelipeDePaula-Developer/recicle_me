package com.healthcare.cadusers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;


@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iduser;
    @Column
    private String name;
    @Column
    private String email;
    @Column(columnDefinition = "CHAR",length = 10)
    private String cpf;
    @Column(columnDefinition = "CHAR default 'T'", length = 1, nullable = false)
    private String status;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP", name = "insert_timestamp")
    private String insertTimestamp;

    public User(){

    }

    public User(String name, String email, String cpf, String status) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.status = status;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInsertTimestamp() {
        return insertTimestamp;
    }
}
