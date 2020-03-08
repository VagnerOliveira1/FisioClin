package com.rightside.fisioclin.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Estado_info implements Serializable {
    private String area_km2;
    private String codigo_ibge;
    private String nome;


    // Getter Methods

    public String getArea_km2() {
        return area_km2;
    }

    public String getCodigo_ibge() {
        return codigo_ibge;
    }

    public String getNome() {
        return nome;
    }

    // Setter Methods

    public void setArea_km2( String area_km2 ) {
        this.area_km2 = area_km2;
    }

    public void setCodigo_ibge( String codigo_ibge ) {
        this.codigo_ibge = codigo_ibge;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }
}
