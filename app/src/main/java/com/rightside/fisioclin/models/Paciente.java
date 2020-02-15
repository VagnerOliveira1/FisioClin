package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Paciente extends User implements Serializable {


    private int sessoes = 1;
    private DiagnosticoMedico diagnosticoMedico;

    public Paciente() {
    }


    public Paciente(User user, DiagnosticoMedico diagnosticoMedico, int sessoes) {
        super(user.getId(), user.getName(), user.getProfilePictureUrl(), user.getEmail(), user.getPhoneNumber(), user.getProfissao(), user.getDataNascimento(),user.getSexo(), false);
        this.sessoes = sessoes;
        this.diagnosticoMedico = diagnosticoMedico;

    }

    public Paciente(String id, String name, String profilePictureUrl, String email) {
        super(id, name, profilePictureUrl, email);
        setAdmin(false);
    }




    public int getSessoes() {
        return sessoes;
    }

    public void setSessoes(int sessoes) {
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
        return pacient;
    }





}
