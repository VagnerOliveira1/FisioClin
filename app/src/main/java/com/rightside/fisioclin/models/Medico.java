package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.HashMap;

public class Medico extends User implements Serializable {

    private String crefito = "";
    private Clinica clinica;
    private Pontuacao pontuacao;
    private FisioPoints fisioPoints;
    private int notificacao = 0;
    private int relatorio = 0;


    public Medico() {

    }

    public Medico(String id, String name, String profilePictureUrl, String email, String crefito) {
        super(id, name, profilePictureUrl, email);
        this.crefito = crefito;
        setAdmin(true);
    }

    public Medico(String id, String name, String profilePictureUrl, String email) {
        super(id, name, profilePictureUrl, email);
        setAdmin(true);
    }

    public Medico(String id, String name, String profilePictureUrl) {
        super(id, name, profilePictureUrl);
        setAdmin(true);
    }

    public int getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(int notificacao) {
        this.notificacao = notificacao;
    }

    public int getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(int relatorio) {
        this.relatorio = relatorio;
    }

    public Pontuacao getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Pontuacao pontuacao) {
        this.pontuacao = pontuacao;
    }

    public boolean contain(String text) {
        return getName().toLowerCase().contains(text.toLowerCase());
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    public FisioPoints getFisioPoints() {
        return fisioPoints;
    }

    public void setFisioPoints(FisioPoints fisioPoints) {
        this.fisioPoints = fisioPoints;
    }

    @Override
    public void setAdmin(boolean admin) {
        super.setAdmin(admin);
    }


    public String getCrefito() {
        return crefito;
    }

    public void setCrefito(String crefito) {
        this.crefito = crefito;
    }

    public HashMap<String, Object> returnDoctor() {
        HashMap<String, Object> doctor = new HashMap<>();
        doctor.put("id", getId());
        doctor.put("name", getName());
        doctor.put("profilePictureUrl", getProfilePictureUrl());
        doctor.put("phoneNumber", getPhoneNumber());
        doctor.put("crefito", getCrefito());
        doctor.put("endereco", getEndereco());
        doctor.put("clinica", getClinica());
        doctor.put("pontuacao", getPontuacao());
        doctor.put("relatorio", getRelatorio());
        doctor.put("notificacao", getNotificacao());
        doctor.put("fisioPoints", getFisioPoints());
        doctor.put("email", getEmail());
        return doctor;
    }

}
