package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ficha implements Serializable {

    private List<Consulta> consulta = new ArrayList<>();
    private List<String> comentarioPosConsulta = new ArrayList<>();
    private Paciente paciente;

    public Ficha() {
    }

    public Ficha(Consulta consulta, Paciente paciente, String comentario) {
        this.paciente = paciente;
        this.consulta.add(consulta);
        this.comentarioPosConsulta.add(comentario);
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

    public List<String> getComentarioPosConsulta() {
        return comentarioPosConsulta;
    }

    public void setComentarioPosConsulta(List<String> comentarioPosConsulta) {
        this.comentarioPosConsulta = comentarioPosConsulta;
    }

    public HashMap<String, Object> returnFicha() {
        HashMap<String, Object> ficha = new HashMap<>();
        ficha.put("paciente", getPaciente());
        ficha.put("consulta", getConsulta());
        ficha.put("comentarioPosConsulta", getComentarioPosConsulta());
        return ficha;
    }
}
