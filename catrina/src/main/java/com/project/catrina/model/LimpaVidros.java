package com.project.catrina.model;

public class LimpaVidros extends Robo{

    public LimpaVidros(int id, String nome, double versao, int bateria, int potencia) {
        super(id, nome, versao, bateria, potencia);
    }

    public String limparVidro(int quantidadeVidros){
        if(getBateria() > 0){
            setBateria(getBateria() - getPotencia() * quantidadeVidros);
            return quantidadeVidros > 1? quantidadeVidros + " vidros limpos  | Bateria atual: " + getBateria() :quantidadeVidros + " vidro limpo  |  " + "Bateria atual: " + getBateria();
        }

        return "Sem bateria!!";
    }

    public String recarregarEnergia() {
        if(getBateria() < 100){
            if(getBateria() >= 95){
                setBateria(100);
            }else{
                setBateria(getBateria() + 5);
            }
            return "Bateria atual: " + getBateria();
        }

        return "Carga da bateria completa!";
    }
}
