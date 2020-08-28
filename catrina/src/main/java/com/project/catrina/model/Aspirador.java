package com.project.catrina.model;

public class Aspirador extends Robo {

    public Aspirador(int id, String nome, double versao, int bateria, int potencia) {
        super(id, nome, versao, bateria, potencia);
    }

    public String aspirar(int horasLigado){

        if(getBateria() - getPotencia() / horasLigado > 0){
            setBateria(getBateria() - getPotencia() * horasLigado);

            return horasLigado > 1? horasLigado + " horas limpando | Bateria atual: " + getBateria() : horasLigado + " hora limpando | Bateria atual: " + getBateria();
        }

        return "Sem bateria para executar essa ação!!";
    }

    public String recarregarEnergia() {
        if(getBateria() < 100){
            if(getBateria() >= 80){
                setBateria(100);
            }else{
                setBateria(getBateria() + 20);
            }
            return "Bateria atual: " + getBateria();
        }

        return "Carga da bateria completa!";
    }
}
