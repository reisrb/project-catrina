package com.project.catrina.model;

import com.project.catrina.model.Compromissos;
import com.project.catrina.model.Robo;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorDeAgenda extends Robo {

    private List<Compromissos> compromissosList;

    public GerenciadorDeAgenda(int id, String nome, double versao, int bateria, int potencia) {
        super(id, nome, versao, bateria, potencia);
        compromissosList = new ArrayList<Compromissos>();
    }

    public String criarLembrete(Compromissos compromissos){
        compromissosList.add(compromissos);

        if(getBateria() - getPotencia() > 0){
            setBateria(getBateria() - getPotencia());

            return "O lembrete '" + compromissos.getNome() + "' foi criado com sucesso! | Bateria atual: " + getBateria();
        }

        return "Sem bateria para executar essa ação!!";
    }

    public List<Compromissos> exibirCompromissos(){
        return compromissosList;
    }

    public String recarregarEnergia() {
        if(getBateria() < 100){
            if(getBateria() >= 70){
                setBateria(100);
            }else{
                setBateria(getBateria() + 30);
            }
            return "Bateria atual: " + getBateria();
        }

        return "Carga da bateria completa!";
    }
}
