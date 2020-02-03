package com.rightside.fisioclin.models;

import java.util.HashMap;

public class Consulta {

    private Horario horario;
    private Paciente paciente;

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }


    public HashMap<String, Object> returnConsulta() {
        HashMap<String, Object> consulta = new HashMap<>();
        consulta.put("Dia", getHorario().getDiaDaSemanaFormatado());
        consulta.put("Data", getHorario().getDataFormatada());
        consulta.put("Hora", getHorario().getHoraFormatada());
        consulta.put("pacinte",getPaciente().returnPacient());

        return consulta;
    }



}
