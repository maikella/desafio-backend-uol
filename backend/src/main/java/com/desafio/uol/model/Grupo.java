package com.desafio.uol.model;

public enum Grupo {
    LIGA_DA_JUSTICA ("Liga da Justiça"), OS_VINGADORES ("Os Vingadores");

    String nome;

    Grupo(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
