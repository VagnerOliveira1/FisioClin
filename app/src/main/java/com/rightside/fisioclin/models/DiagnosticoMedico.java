package com.rightside.fisioclin.models;

public class DiagnosticoMedico {

    private String descricaoMedica = "";
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
