package com.example.appbuffetfirebase;

public class Crianca {
    int id = 1;
    private String nomeCrianca;
    private String nomeResponsavel;
    private String telefone;
    private String dtNasc;

    public Crianca(String nomeCrianca, String nomeResponsavel, String telefone, String dtNasc) {
        this.nomeCrianca = nomeCrianca;
        this.nomeResponsavel = nomeResponsavel;
        this.telefone = telefone;
        this.dtNasc = dtNasc;
    }

    public Crianca() {
    }

    public String getNomeCrianca() {
        return nomeCrianca;
    }

    public void setNomeCrianca(String nomeCrianca) {
        this.nomeCrianca = nomeCrianca;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
