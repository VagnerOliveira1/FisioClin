package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Consulta implements Serializable {


    public Consulta() {
    }
    private String comentarioPosConsulta = "";
    private boolean avaliada = false;

    public Consulta(Horario horario, Paciente paciente) {
        this.horario = horario;
        this.paciente = paciente;
    }

    public Consulta(Horario horario, Paciente paciente, String comentario) {
        this.horario = horario;
        this.paciente = paciente;
        this.comentarioPosConsulta = comentario;
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


    public String getComentarioPosConsulta() {
        return comentarioPosConsulta;
    }

    public void setComentarioPosConsulta(String comentarioPosConsulta) {
        this.comentarioPosConsulta = comentarioPosConsulta;
    }

    public boolean isAvaliada() {
        return avaliada;
    }

    public void setAvaliada(boolean avaliada) {
        this.avaliada = avaliada;
    }

    public HashMap<String, Object> returnConsulta() {
        HashMap<String, Object> consulta = new HashMap<>();
        consulta.put("horario", horario);
        consulta.put("paciente", paciente);
        consulta.put("comentarioPosConsulta", getComentarioPosConsulta());
        return consulta;
    }



}
