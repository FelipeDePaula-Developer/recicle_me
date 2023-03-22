package com.healthcare.cadusers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity(name = "clinics")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idclinic;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "CHAR", length = 14, nullable = false)
    private String cnpj;

    public Clinic() {
    }

    public Clinic(String name, String cnpj) {
        this.name = name;
        this.cnpj = cnpj;
    }

    public Integer getIdclinic() {
        return idclinic;
    }

    public void setIdclinic(Integer idclinic) {
        this.idclinic = idclinic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
