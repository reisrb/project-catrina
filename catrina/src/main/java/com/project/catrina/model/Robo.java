package com.project.catrina.model;

public abstract class Robo {
    private int id;
    private String nome;
    private double versao;
    private int bateria;
    private int potencia;

    public Robo(int id, String nome, double versao, int bateria, int potencia) {
        this.id = id;
        this.nome = nome;
        this.versao = versao;
        this.bateria = bateria;
        this.potencia = potencia;
    }

    public abstract String recarregarEnergia();

    @Override
    public String toString() {
        return "Robo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", versao=" + versao +
                ", bateria=" + bateria +
                ", potencia=" + potencia +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getVersao() {
        return versao;
    }

    public void setVersao(double versao) {
        this.versao = versao;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }
}
