package com.project.catrina.controller;

import com.project.catrina.model.Aspirador;
import com.project.catrina.model.GerenciadorDeAgenda;
import com.project.catrina.model.LimpaVidros;
import com.project.catrina.model.Robo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/limpaVidros")
public class LimpaVidrosController {

    List<Robo> limpaVidrosList = new ArrayList<>();

    public LimpaVidrosController() {
        limpaVidrosList.add(new LimpaVidros(1, "robson", 5.2, 100, 5));
    }

    @GetMapping
    public List<Robo> exibeTodos() {
        return limpaVidrosList;
    }

    @PostMapping
    public String cadastrar(@RequestBody LimpaVidros limpaVidros) {

        for (Robo r : limpaVidrosList) {
            if (limpaVidros.getId() != r.getId()) {
                limpaVidrosList.add(limpaVidros);
                return "robô adicionado";
            }
        }

        for (Robo r : limpaVidrosList) {
            if (limpaVidros.getId() == r.getId()) {
                return "Essa identificação de robô já está em nossa base de dados como:\n" + r;
            }
        }

        return "Houve uma falha nos dados!";
    }

    @PutMapping("/{id}")
    public String alterarRobo(@RequestBody LimpaVidros limpaVidros, @PathVariable int id) {

        for (Robo r : limpaVidrosList) {
            if (r.getId() == id) {
                limpaVidrosList.set(limpaVidrosList.indexOf(r), limpaVidros);
                return "Robô alterado com sucesso! Dados antigos: \n" + r;
            }
        }

        return "Id do robô não existe!";
    }

    @DeleteMapping("/{id}")
    public String deletarRobo(@PathVariable int id) {

        for (Robo r : limpaVidrosList) {
            if (r.getId() == id) {
                limpaVidrosList.remove(limpaVidrosList.indexOf(r));
                return "Robo deletado com sucesso";
            }

        }
        return "Falha ao deletar";
    }

    @PostMapping("{id}/limpar/{quantidadeVidros}")
    public String limparVidro(@PathVariable int id, @PathVariable int quantidadeVidros) {

        for (Robo r : limpaVidrosList) {
            if (r.getId() == id) {
                return ((LimpaVidros) r).limparVidro(quantidadeVidros);
            }
        }

        return "Robô não cadastrado!";
    }

    @PostMapping("/recarregarBateria/{id}")
    public String recarregarBateria(@PathVariable int id) {
        for (Robo r : limpaVidrosList) {
            if (r.getId() == id) {
                return "Percentual carregado! " + r.recarregarEnergia();
            }
        }
        return "Robô não cadastrado!";
    }
}
