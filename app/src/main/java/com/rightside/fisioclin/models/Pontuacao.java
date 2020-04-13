package com.rightside.fisioclin.models;

import java.io.Serializable;
import java.util.HashMap;

public class Pontuacao implements Serializable {
    private int pontos = 0;
    private int quantidadeDeVotos = 0;
    private int media = 0;


    public int getPontos() {
        return pontos;
    }


    public Pontuacao(int pontos, int quantidadeDeVotos, int media) {
        this.pontos = pontos;
        this.quantidadeDeVotos = quantidadeDeVotos;
        this.media = media;

    }

    public Pontuacao() {

    }

    public void setPontos(int pontos) {
        this.pontos =  getPontos() + pontos;
    }

    public int getQuantidadeDeVotos() {
        return quantidadeDeVotos;
    }

    public void setQuantidadeDeVotos(int quantidadeDeVotos) {
        this.quantidadeDeVotos = getQuantidadeDeVotos() + quantidadeDeVotos;
    }

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }

    public HashMap<String, Object> returnPontuacao() {
        HashMap<String, Object> pontuacao = new HashMap<>();
        pontuacao.put("quantidadeDeVotos", getQuantidadeDeVotos());
        pontuacao.put("pontos", getPontos());
        pontuacao.put("media", getMedia());
        return pontuacao;
    }

    @Override
    public String toString() {
        return "Pontuacao{" +
                "pontos=" + pontos +
                ", quantidadeDeVotos=" + quantidadeDeVotos +
                '}';
    }


}
