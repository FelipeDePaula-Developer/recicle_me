package com.healthcare.cadusers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;



@Entity(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idclient;

    @Column(length = 150)
    private String nconvenio;
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


    public Client() {

    }

    public Client(String nconvenio, String name, String email, String cpf, String status) {
        this.nconvenio = nconvenio;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.status = status;
    }

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public String getNconvenio() {
        return nconvenio;
    }

    public void setNconvenio(String nconvenio) {
        this.nconvenio = nconvenio;
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
