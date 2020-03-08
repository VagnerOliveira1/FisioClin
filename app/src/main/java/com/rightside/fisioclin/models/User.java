package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {

    private String id;
    private String name;
    private String profilePictureUrl;
    private String email;
    private String phoneNumber = "";
    private String profissao = "";
    private String dataNascimento = "";
    private String sexo = "";
    private String token = "";
    private boolean admin;
    private Endereco endereco;

    public User() {
        setAdmin(false);
    }

    public User(String id, String name, String profilePictureUrl, String email, String phoneNumber, String profissao, String dataNascimento, String sexo, String token, boolean admin, Endereco endereco) {
        this.id = id;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profissao = profissao;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.token = token;
        this.admin = admin;
        this.endereco = endereco;
    }

    public User(String id, String name, String profilePictureUrl, String email, String phoneNumber, String profissao, String dataNascimento, String sexo, String token, boolean admin) {
        this.id = id;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profissao = profissao;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.token = token;
        this.admin = admin;

    }

    public User(String id, String name, String profilePictureUrl, String email) {
        this.id = id;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        this.email = email;
    }

    public User(String id, String name, String profilePictureUrl) {
        this.id = id;
        this.name = name;
        this.profilePictureUrl = profilePictureUrl;
        setAdmin(false);
    }

    public HashMap<String, Object> returnUser() {
        HashMap<String, Object> user = new HashMap<>();
        user.put("id", getId());
        user.put("name", getName());
        user.put("phoneNumber", getPhoneNumber());
        user.put("profissao", getProfissao());
        user.put("sexo", getSexo());
        user.put("dataNascimento", getDataNascimento());
        user.put("email", getEmail());
        user.put("profilePictureUrl", getProfilePictureUrl());
        user.put("token", getToken());
        user.put("endereco", getEndereco());
        return user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
