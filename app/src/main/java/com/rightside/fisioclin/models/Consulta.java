package com.rightside.fisioclin.models;

import java.util.HashMap;
import java.util.List;

public class Consulta {

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
