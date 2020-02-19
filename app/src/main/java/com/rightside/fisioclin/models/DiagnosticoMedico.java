package com.rightside.fisioclin.models;

import java.io.Serializable;

public class DiagnosticoMedico implements Serializable {

    private String descricaoMedica = "Não possui";
    private String queixa = "";


    public DiagnosticoMedico() {
    }

    public DiagnosticoMedico(String descricaoMedica, String queixa) {
        this.descricaoMedica = descricaoMedica;
        this.queixa = queixa;
    }

    public String getDescricaoMedica() {
        return descricaoMedica;
    }

    public void setDescricaoMedica(String descricaoMedica) {
        this.descricaoMedica = descricaoMedica;
    }

    public String getQueixa() {
        return queixa;
    }

    public void setQueixa(String queixa) {
        this.queixa = queixa;
    }



}
