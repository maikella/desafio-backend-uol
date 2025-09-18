package com.desafio.uol.model;

public class JogadorModel {
    private String codinome, nome, email, telefone, grupo;

    public JogadorModel(String codinome, String nome, String email, String telefone, String grupo) {
        this.codinome = codinome;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.grupo = grupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
