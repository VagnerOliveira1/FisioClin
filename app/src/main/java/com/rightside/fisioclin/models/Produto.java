package com.rightside.fisioclin.models;

import java.io.Serializable;

public class Produto implements Serializable {
    private String nome;
    private int preco;
    private int tipo;
    private int quantidade;


    public Produto() {
    }



    public Produto(String nome, int preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }


    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}
