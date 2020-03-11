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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bairro",
        "cidade",
        "logradouro",
        "estado_info",
        "cep",
        "cidade_info",
        "estado"
})
public class Endereco implements Serializable {

    @JsonProperty("bairro")
    private String bairro = "";
    @JsonProperty("cidade")
    private String cidade = "";
    @JsonProperty("logradouro")
    private String logradouro = "";
    @JsonProperty("estado_info")
    private Estado_info estado_info;
    @JsonProperty("cep")
    private String cep = "";
    @JsonProperty("cidade_info")
    private Cidade_info cidade_info;
    @JsonProperty("estado")
    private String estado = "";
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    @JsonIgnore
    private String numero = "";

    @JsonProperty("bairro")
    public String getBairro() {
        return bairro;
    }

    @JsonProperty("bairro")
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @JsonProperty("cidade")
    public String getCidade() {
        return cidade;
    }

    @JsonProperty("cidade")
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @JsonProperty("logradouro")
    public String getLogradouro() {
        return logradouro;
    }

    @JsonProperty("logradouro")
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }



    @JsonProperty("cep")
    public String getCep() {
        return cep;
    }

    @JsonProperty("cep")
    public void setCep(String cep) {
        this.cep = cep;
    }

    public Estado_info getEstado_info() {
        return estado_info;
    }

    public void setEstado_info(Estado_info estado_info) {
        this.estado_info = estado_info;
    }

    public Cidade_info getCidade_info() {
        return cidade_info;
    }

    public void setCidade_info(Cidade_info cidade_info) {
        this.cidade_info = cidade_info;
    }

    @JsonProperty("estado")
    public String getEstado() {
        return estado;
    }

    @JsonProperty("estado")
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    @JsonAnyGetter
    public String getNumero() {
        return numero;
    }
    @JsonAnySetter
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
