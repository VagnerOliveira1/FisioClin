package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.HashMap;

public class Clinica implements Serializable {
    private String nome = "";
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public HashMap<String, Object> returnClinica() {
        HashMap<String, Object> doctor = new HashMap<>();
        doctor.put("nome", getNome());
        doctor.put("endereco", getEndereco());
        return doctor;
    }
}
