package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.HashMap;

public class Consulta implements Serializable {


    public Consulta() {
    }

    public Consulta(Horario horario, Paciente paciente) {
        this.horario = horario;
        this.paciente = paciente;
    }

    private Horario horario;
    private Paciente paciente;

    public Horario getHorario() {
        return horario;
    }


    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }


    public HashMap<String, Object> returnConsulta() {
        HashMap<String, Object> consulta = new HashMap<>();
        consulta.put("horario", horario);
        consulta.put("paciente", paciente);
        return consulta;
    }



}
