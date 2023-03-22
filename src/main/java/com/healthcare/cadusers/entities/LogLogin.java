package com.healthcare.cadusers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity(name = "logins_log")
public class LogLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idlogin;
    @ManyToOne
    @JoinColumn(name="idcredential", referencedColumnName = "idcredential")
    private Credential credential;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP", name = "time_login")
    private String timeLogin;
    @Column(columnDefinition="TIMESTAMP", name = "time_logoff")
    private String timeLogoff;

    public LogLogin() {
    }

    public LogLogin(Credential credential, String timeLogin, String timeLogoff) {
        this.credential = credential;
        this.timeLogin = timeLogin;
        this.timeLogoff = timeLogoff;
    }

    public Integer getIdlogin() {
        return idlogin;
    }

    public void setIdlogin(Integer idlogin) {
        this.idlogin = idlogin;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getTimeLogin() {
        return timeLogin;
    }

    public void setTimeLogin(String timeLogin) {
        this.timeLogin = timeLogin;
    }

    public String getTimeLogoff() {
        return timeLogoff;
    }

    public void setTimeLogoff(String timeLogoff) {
        this.timeLogoff = timeLogoff;
    }
}
