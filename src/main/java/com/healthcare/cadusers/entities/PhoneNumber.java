package com.healthcare.cadusers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;


@Entity
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idphone;
    @ManyToOne
    @JoinColumn(name = "idclient", referencedColumnName = "idclient")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    private User user;
    @ManyToOne
    @JoinColumn(name = "iddoctor", referencedColumnName = "iddoctor")
    private Doctor doctor;

    @Column(length = 3, name="phone_ddi")
    private String phoneDDI;
    @Column(length = 3, name="phone_ddd")
    private String phoneDDD;
    @Column(length = 10, name="phone_number")
    private String phoneNumber;
    @Column(length = 2, name="phone_type")
    private String phoneType;
    @Column(columnDefinition = "CHAR default 'T'", length = 1)
    private String status;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP", name = "insert_timestampp")
    private String insertTimestamp;

    public PhoneNumber() {
    }

    public PhoneNumber(Client client, User user, Doctor doctor, String phoneDDI, String phoneDDD, String phoneNumber, String phoneType, String status, String insertTimestamp) {
        this.client = client;
        this.user = user;
        this.doctor = doctor;
        this.phoneDDI = phoneDDI;
        this.phoneDDD = phoneDDD;
        this.phoneNumber = phoneNumber;
        this.phoneType = phoneType;
        this.status = status;
        this.insertTimestamp = insertTimestamp;
    }

    public Integer getIdphone() {
        return idphone;
    }

    public void setIdphone(Integer idphone) {
        this.idphone = idphone;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getPhoneDDI() {
        return phoneDDI;
    }

    public void setPhoneDDI(String phoneDDI) {
        this.phoneDDI = phoneDDI;
    }

    public String getPhoneDDD() {
        return phoneDDD;
    }

    public void setPhoneDDD(String phoneDDD) {
        this.phoneDDD = phoneDDD;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
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

    public void setInsertTimestamp(String insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }
}
