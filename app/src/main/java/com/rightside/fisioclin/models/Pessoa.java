package com.rightside.fisioclin.models;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private String id;
    private String name;
    private String profilePictureUrl;
    private String email;
    private String phoneNumber = "";
    private String profissao = "";
    private String dataNascimento = "";
    private String sexo = "";
    private boolean admin;

    public Pessoa() {
        setAdmin(false);
    }

    public Pessoa(String id, String name, String profilePictureUrl, String email, String phoneNumber, String profissao, String dataNascimento, String sexo, boolean admin) {
        this.id = id;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profissao = profissao;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.admin = admin;
    }

    public Pessoa(String id, String name, String profilePictureUrl, String email) {
        this.id = id;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.email = email;
    }

    public Pessoa(String id, String name, String profilePictureUrl) {
        this.id = id;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        setAdmin(false);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
