package com.healthcare.cadusers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


@Entity(name = "clinic_doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddoctor;
    @Column(length = 100)
    private String name;
    @Column(length = 50)
    private String licenseRegistration;
    @Column(columnDefinition = "CHAR default 'T'", length = 1, nullable = false)
    private String status;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP", name = "insert_timestamp")
    private String insertTimestamp;

    @ManyToOne
    @JoinColumn(name = "idclinic", referencedColumnName = "idclinic")
    private Clinic clinic;
    @ManyToOne
    @JoinColumn(name = "idspeciality", referencedColumnName = "idspeciality")
    private MedicineSpeciality medicineSpeciality;

    public Doctor() {
    }

    public Doctor(String name, String licenseRegistration, String status, Clinic clinic, MedicineSpeciality medicineSpeciality) {
        this.name = name;
        this.licenseRegistration = licenseRegistration;
        this.status = status;
        this.clinic = clinic;
        this.medicineSpeciality = medicineSpeciality;
    }

    public Integer getIddoctor() {
        return iddoctor;
    }

    public void setIddoctor(Integer iddoctor) {
        this.iddoctor = iddoctor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicenseRegistration() {
        return licenseRegistration;
    }

    public void setLicenseRegistration(String licenseRegistration) {
        this.licenseRegistration = licenseRegistration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
