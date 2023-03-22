package com.healthcare.cadusers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;


@Entity(name = "medicine_speciality")
public class MedicineSpeciality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idspeciality;
    @Column(length = 100)
    private String specialityDesc;

    public MedicineSpeciality() {
    }

    public MedicineSpeciality(String specialityDesc) {
        this.specialityDesc = specialityDesc;
    }

    public Integer getIdspeciality() {
        return idspeciality;
    }

    public void setIdspeciality(Integer idspeciality) {
        this.idspeciality = idspeciality;
    }

    public String getSpecialityDesc() {
        return specialityDesc;
    }

    public void setSpecialityDesc(String specialityDesc) {
        this.specialityDesc = specialityDesc;
    }
}
