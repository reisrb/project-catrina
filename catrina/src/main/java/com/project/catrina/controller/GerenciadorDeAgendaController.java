package com.project.catrina.controller;

import com.project.catrina.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gerenciadorDeAgenda")
public class GerenciadorDeAgendaController {
    List<Robo> gerenciadorDeAgendaList = new ArrayList<>();

    @GetMapping
    public List<Robo> getGerenciadorDeAgendaList() {
        Robo r1 = new GerenciadorDeAgenda(1, "robson", 5.2, 100, 5);

        if (gerenciadorDeAgendaList.isEmpty()) {
            gerenciadorDeAgendaList.add(r1);
        }

        return gerenciadorDeAgendaList;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody GerenciadorDeAgenda gerenciadorDeAgenda) {

        if (gerenciadorDeAgendaList.isEmpty()) {
            gerenciadorDeAgendaList.add(gerenciadorDeAgenda);
            return "robô adicionado";
        } else {
            for (Robo g : gerenciadorDeAgendaList) {
                if (gerenciadorDeAgenda.getId() != g.getId()) {
                    gerenciadorDeAgendaList.add(gerenciadorDeAgenda);
                    return "robô adicionado";
                }
            }
        }

        for (Robo g : gerenciadorDeAgendaList) {
            if (gerenciadorDeAgenda.getId() == g.getId()) {
                return "Essa identificação de robô já está em nossa base de dados como:\n" + g;
            }
        }

        return "Houve uma falha nos dados!";
    }

    @PutMapping("/alterar/{id}")
    public String alterarRobo(@RequestBody GerenciadorDeAgenda gerenciadorDeAgenda, @PathVariable int id) {

        for (Robo g : gerenciadorDeAgendaList) {
            if (g.getId() == id) {
                gerenciadorDeAgendaList.set(gerenciadorDeAgendaList.indexOf(g), gerenciadorDeAgenda);
                return "Robô alterado com sucesso! Dados antigos: \n" + g;
            }
        }

        return "Id do robô não existe!";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarRobo(@PathVariable int id) {

        for (Robo g : gerenciadorDeAgendaList) {
            if (g.getId() == id) {
                gerenciadorDeAgendaList.remove(gerenciadorDeAgendaList.indexOf(g));
                return "Robo deletado com sucesso";
            }

        }
        return "Falha ao deletar";
    }

    @PostMapping("{id}/compromisso")
    public String criarCompromisso(@PathVariable int id, @RequestBody Compromissos compromissos) {

        for (Robo g : gerenciadorDeAgendaList) {
            if (g.getId() == id) {
                if (g instanceof GerenciadorDeAgenda) {
                    return ((GerenciadorDeAgenda) g).criarLembrete(compromissos);
                }
            }
        }

        return "Robô não cadastrado!";
    }

    @GetMapping("{id}/exibirCompromissos")
    public List<Compromissos> exibirCompromissos(@PathVariable int id) {
        List<Compromissos> atuais = new ArrayList<>();

        for (Robo g : gerenciadorDeAgendaList) {
            if (g.getId() == id) {
                if (g instanceof GerenciadorDeAgenda) {
                    return ((GerenciadorDeAgenda) g).exibirCompromissos();
                }
            }
        }

        return atuais;
    }

    @PostMapping("{id}/alterarCompromisso/{idCompromisso}")
    public String alterarCompromisso(@PathVariable int id, @PathVariable int idCompromisso, @RequestBody Compromissos compromissos){

        for (Robo g : gerenciadorDeAgendaList) {
            if (g.getId() == id) {
                return ((GerenciadorDeAgenda) g).alterarLembrete(compromissos, idCompromisso);
            }
        }

        return "Id do robô não existe!";
    }

    @PostMapping("/recarregarBateria/{id}")
    public String recarregarBateria(@PathVariable int id) {
        for (Robo g : gerenciadorDeAgendaList) {
            if (g.getId() == id) {
                return "Percentual carregado! " + g.recarregarEnergia();
            }
        }
        return "Robô não cadastrado!";
    }
}
