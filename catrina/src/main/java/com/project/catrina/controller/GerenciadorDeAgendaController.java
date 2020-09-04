package com.project.catrina.controller;

import com.project.catrina.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gerenciadorDeAgenda")
public class GerenciadorDeAgendaController {
    List<Robo> gerenciadorDeAgendaList = new ArrayList<>();

    public GerenciadorDeAgendaController() {
        gerenciadorDeAgendaList.add(new GerenciadorDeAgenda(1, "robson", 5.2, 100, 5));
    }

    @GetMapping
    public List<Robo> getGerenciadorDeAgendaList() {
        return gerenciadorDeAgendaList;
    }

    @PostMapping
    public String cadastrar(@RequestBody GerenciadorDeAgenda gerenciadorDeAgenda) {

        for (Robo r : gerenciadorDeAgendaList) {
            if (gerenciadorDeAgenda.getId() != r.getId()) {
                gerenciadorDeAgendaList.add(gerenciadorDeAgenda);
                return "robô adicionado";
            }
        }

        for (Robo r : gerenciadorDeAgendaList) {
            if (gerenciadorDeAgenda.getId() == r.getId()) {
                return "Essa identificação de robô já está em nossa base de dados como:\n" + r;
            }
        }

        return "Houve uma falha nos dados!";
    }

    @PutMapping("/{id}")
    public String alterarRobo(@RequestBody GerenciadorDeAgenda gerenciadorDeAgenda, @PathVariable int id) {

        for (Robo r : gerenciadorDeAgendaList) {
            if (r.getId() == id) {
                gerenciadorDeAgendaList.set(gerenciadorDeAgendaList.indexOf(r), gerenciadorDeAgenda);
                return "Robô alterado com sucesso! Dados antigos: \n" + r;
            }
        }

        return "Id do robô não existe!";
    }

    @DeleteMapping("/{id}")
    public String deletarRobo(@PathVariable int id) {

        for (Robo r : gerenciadorDeAgendaList) {
            if (r.getId() == id) {
                gerenciadorDeAgendaList.remove(gerenciadorDeAgendaList.indexOf(r));
                return "Robo deletado com sucesso";
            }

        }
        return "Falha ao deletar";
    }

    @PostMapping("{id}/compromisso")
    public String criarCompromisso(@PathVariable int id, @RequestBody Compromissos compromissos) {

        for (Robo r : gerenciadorDeAgendaList) {
            if (r.getId() == id) {
                if (r instanceof GerenciadorDeAgenda) {
                    return ((GerenciadorDeAgenda) r).criarLembrete(compromissos);
                }
            }
        }

        return "Robô não cadastrado!";
    }

    @GetMapping("{id}/exibirCompromissos")
    public List<Compromissos> exibirCompromissos(@PathVariable int id) {
        List<Compromissos> atuais = new ArrayList<>();

        for (Robo r : gerenciadorDeAgendaList) {
            if (r.getId() == id) {
                if (r instanceof GerenciadorDeAgenda) {
                    return ((GerenciadorDeAgenda) r).exibirCompromissos();
                }
            }
        }

        return atuais;
    }

    @PostMapping("{id}/alterarCompromisso/{idCompromisso}")
    public String alterarCompromisso(@PathVariable int id, @PathVariable int idCompromisso, @RequestBody Compromissos compromissos) {

        for (Robo r : gerenciadorDeAgendaList) {
            if (r.getId() == id) {
                return ((GerenciadorDeAgenda) r).alterarLembrete(compromissos, idCompromisso);
            }
        }

        return "Id do robô não existe!";
    }

    @DeleteMapping("{id}/deletarCompromisso/{idCompromisso}")
    public String deletarCompromisso(@PathVariable int id, @PathVariable int idCompromisso) {
        for (Robo r : gerenciadorDeAgendaList) {
            if (r.getId() == id) {
                return ((GerenciadorDeAgenda) r).deletarLembrete(idCompromisso);
            }
        }

        return "Id do robô não existe!";
    }

    @PostMapping("/recarregarBateria/{id}")
    public String recarregarBateria(@PathVariable int id) {
        for (Robo r : gerenciadorDeAgendaList) {
            if (r.getId() == id) {
                return "Percentual carregado! " + r.recarregarEnergia();
            }
        }
        return "Robô não cadastrado!";
    }
}
