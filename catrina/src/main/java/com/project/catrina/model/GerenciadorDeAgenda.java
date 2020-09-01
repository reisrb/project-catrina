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

    public String criarLembrete(Compromissos compromissos) {
        boolean existe = false;

        for (Compromissos c : compromissosList) {
            if (c.getId() == compromissos.getId()) {
                existe = true;
            }
        }

        if (!existe) {
            compromissosList.add(compromissos);
            if (getBateria() - getPotencia() > 0) {
                setBateria(getBateria() - getPotencia());

                return "O lembrete '" + compromissos.getNome() + "' foi criado com sucesso! | Bateria atual: " + getBateria();
            }
        } else {
            return "O id do lembrete já existe";
        }


        return "Sem bateria para executar essa ação!!";
    }

    public String alterarLembrete(Compromissos compromissos, int idCompromisso){
        for (Compromissos c : compromissosList) {
            if (c.getId() == idCompromisso) {
                compromissosList.set(compromissosList.indexOf(c), compromissos);
                return "Compromisso alterado com sucesso! Nome do compromisso: \n" + compromissos.getNome();
            }
        }

        return "Id do compromisso não existe!";
    }

    public String deletarLembrete(int idCompromisso){
        for (Compromissos c : compromissosList) {
            if (c.getId() == idCompromisso){
                compromissosList.remove(compromissosList.indexOf(c));
                return "Compromisso excluido com sucesso!";
            }
        }

        return "Id do compromisso não existe!";
    }

    public List<Compromissos> exibirCompromissos() {
        System.out.println(compromissosList.isEmpty());

        if(compromissosList.isEmpty()){
            compromissosList.add(new Compromissos(2, "dar banho no dogzão", "pegar o shampoo e acessorios"));
        }
        return compromissosList;
    }

    public String recarregarEnergia() {
        if (getBateria() < 100) {
            if (getBateria() >= 70) {
                setBateria(100);
            } else {
                setBateria(getBateria() + 30);
            }
            return "Bateria atual: " + getBateria();
        }

        return "Carga da bateria completa!";
    }
}
