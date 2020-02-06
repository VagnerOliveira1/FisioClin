package com.rightside.fisioclin.models;

import java.util.HashMap;

public class Paciente extends Pessoa {

    private String descricaoMedica = "";
    private String queixa = "";
    private String sessoes = "";

    public Paciente() {
    }

    public Paciente(String id, String name, String profilePictureUrl, String email, String phoneNumber, String profissao, String dataNascimento, String sexo, boolean admin, String descricaoMedica, String queixa, String sessoes) {
        super(id, name, profilePictureUrl, email, phoneNumber, profissao, dataNascimento, sexo, admin);
        this.descricaoMedica = descricaoMedica;
        this.queixa = queixa;
        this.sessoes = sessoes;
        setAdmin(false);
    }

    public Paciente(String id, String name, String profilePictureUrl, String email) {
        super(id, name, profilePictureUrl, email);
        setAdmin(false);
    }


    public String getDescricaoMedica() {
        return descricaoMedica;
    }

    public void setDescricaoMedica(String descricaoMedica) {
        this.descricaoMedica = descricaoMedica;
    }

    public String  getSessoes() {
        return sessoes;
    }

    public void setSessoes(String sessoes) {
        this.sessoes = sessoes;
    }

    public String getQueixa() {
        return queixa;
    }

    public void setQueixa(String queixa) {
        this.queixa = queixa;
    }

    public HashMap<String, Object> returnPacient() {
        HashMap<String, Object> pacient = new HashMap<>();
        pacient.put("id", getId());
        pacient.put("name", getName());
        pacient.put("phoneNumber", getPhoneNumber());
        pacient.put("profissao", getProfissao());
        pacient.put("sexo", getSexo());
        pacient.put("sessoes", getSessoes());
        pacient.put("descricaoMedica", getDescricaoMedica());
        pacient.put("queixa", getQueixa());
        pacient.put("dataNascimento", getDataNascimento());
        pacient.put("email", getEmail());
        pacient.put("profilePictureUrl", getProfilePictureUrl());
        return pacient;
    }





}
