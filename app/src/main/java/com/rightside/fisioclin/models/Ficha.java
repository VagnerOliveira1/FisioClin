package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ficha implements Serializable {

    private List<Consulta> consulta = new ArrayList<>();
    private Paciente paciente;


    public Ficha() {
    }

    public Ficha(Consulta consulta, Paciente paciente) {
        this.paciente = paciente;
        this.consulta.add(consulta);

    }

    public boolean contain(String text) {
        return getPaciente().getName().toLowerCase().contains(text);
    }

    public List<Consulta> getConsulta() {
        return consulta;
    }

    public void setConsulta(List<Consulta> consulta) {
        this.consulta = consulta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


    public HashMap<String, Object> returnFicha() {
        HashMap<String, Object> ficha = new HashMap<>();
        ficha.put("paciente", getPaciente());
        ficha.put("consulta", getConsulta());
        return ficha;
    }
}
