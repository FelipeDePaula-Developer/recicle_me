package com.healthcare.cadusers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity(name = "clinics_speciality_relationship")
public class ClinicSpeciality {
    @Column(name = "idclinic_speciality")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idclinicSpeciality;
    @ManyToOne
    @JoinColumn(name = "idclinic", referencedColumnName = "idclinic")
    private Clinic clinic;
    @ManyToOne
    @JoinColumn(name = "idspeciality", referencedColumnName = "idspeciality")
    private MedicineSpeciality medicineSpeciality;

    public ClinicSpeciality() {
    }

    public ClinicSpeciality(Integer idclinicSpeciality, Clinic clinic, MedicineSpeciality medicineSpeciality) {
        this.idclinicSpeciality = idclinicSpeciality;
        this.clinic = clinic;
        this.medicineSpeciality = medicineSpeciality;
    }

    public Integer getIdclinicSpeciality() {
        return idclinicSpeciality;
    }

    public void setIdclinicSpeciality(Integer idclinicSpeciality) {
        this.idclinicSpeciality = idclinicSpeciality;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public MedicineSpeciality getMedicineSpeciality() {
        return medicineSpeciality;
    }

    public void setMedicineSpeciality(MedicineSpeciality medicineSpeciality) {
        this.medicineSpeciality = medicineSpeciality;
    }
}
