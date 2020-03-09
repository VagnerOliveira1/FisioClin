package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Paciente extends User implements Serializable {


    private String sessoes = "";
    private DiagnosticoMedico diagnosticoMedico;

    public Paciente() {
    }


    public Paciente(User user, DiagnosticoMedico diagnosticoMedico, String sessoes) {
        super(user.getId(), user.getName(), user.getProfilePictureUrl(), user.getEmail(), user.getPhoneNumber(), user.getProfissao(), user.getDataNascimento(),user.getSexo(), user.getToken(), false, user.getEndereco());
        this.sessoes = sessoes;
        this.diagnosticoMedico = diagnosticoMedico;

    }

    public Paciente(String id, String name, String profilePictureUrl, String email) {
        super(id, name, profilePictureUrl, email);
        setAdmin(false);
    }




    public String getSessoes() {
        return sessoes;
    }

    public void setSessoes(String sessoes) {
        this.sessoes = sessoes;
    }

    public DiagnosticoMedico getDiagnosticoMedico() {
        return diagnosticoMedico;
    }

    public void setDiagnosticoMedico(DiagnosticoMedico diagnosticoMedico) {
        this.diagnosticoMedico = diagnosticoMedico;
    }

    public HashMap<String, Object> returnPacient() {
        HashMap<String, Object> pacient = new HashMap<>();
        pacient.put("id", getId());
        pacient.put("name", getName());
        pacient.put("phoneNumber", getPhoneNumber());
        pacient.put("profissao", getProfissao());
        pacient.put("sexo", getSexo());
        pacient.put("sessoes", getSessoes());
        pacient.put("dataNascimento", getDataNascimento());
        pacient.put("email", getEmail());
        pacient.put("profilePictureUrl", getProfilePictureUrl());
        pacient.put("diagnosticoMedico", getDiagnosticoMedico());
        pacient.put("token", getToken());
        pacient.put("endereco", getEndereco());
        return pacient;
    }





}
