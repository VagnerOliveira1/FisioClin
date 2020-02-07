package com.rightside.fisioclin.models;

import java.util.HashMap;
import java.util.List;

public class Ficha {

    private String comentarioPosConsulta;
    private Consulta consulta;
    private Paciente paciente;

    public Ficha() {
    }

    public Ficha(String comentarioPosConsulta, Consulta consulta, Paciente paciente) {
        this.comentarioPosConsulta = comentarioPosConsulta;
        this.consulta = consulta;
        this.paciente = paciente;
    }

    public String getComentarioPosConsulta() {
        return comentarioPosConsulta;
    }

    public void setComentarioPosConsulta(String comentarioPosConsulta) {
        this.comentarioPosConsulta = comentarioPosConsulta;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
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
        ficha.put("comentarioPosConulta", getComentarioPosConsulta());
        ficha.put("consulta", getConsulta());
        return ficha;
    }
}
