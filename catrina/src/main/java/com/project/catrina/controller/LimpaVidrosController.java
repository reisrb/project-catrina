package com.project.catrina.controller;

import com.project.catrina.model.Aspirador;
import com.project.catrina.model.LimpaVidros;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/limpaVidros")
public class LimpaVidrosController {

    List<LimpaVidros> limpaVidrosList = new ArrayList<>();

    @GetMapping
    public List<LimpaVidros> exibeTodos(){
        return limpaVidrosList;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@RequestBody LimpaVidros limpaVidros){

        if(limpaVidrosList.isEmpty()){
            limpaVidrosList.add(limpaVidros);
            return "robô adicionado";
        }else{
            for (LimpaVidros l : limpaVidrosList) {
                if( limpaVidros.getId() != l.getId()){
                    limpaVidrosList.add(limpaVidros);
                    return "robô adicionado";
                }
            }
        }

        for (LimpaVidros l: limpaVidrosList) {
            if( limpaVidros.getId() == l.getId()){
                return "Essa identificação de robô já está em nossa base de dados como:\n" + l;
            }
        }

        return "Houve uma falha nos dados!";
    }

    @PutMapping("/alterar/{id}")
    public String alterarRobo(@RequestBody LimpaVidros limpaVidros, @PathVariable int id){

        for (LimpaVidros l: limpaVidrosList) {
            if(l.getId() == id){
                limpaVidrosList.set(limpaVidrosList.indexOf(l), limpaVidros);
                return "Robô alterado com sucesso! Dados antigos: \n" + l;
            }
        }

        return "Id do robô não existe!";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarRobo(@PathVariable int id){

        for (LimpaVidros l : limpaVidrosList) {
            if(l.getId() == id){
                limpaVidrosList.remove(limpaVidrosList.indexOf(l));
                return "Robo deletado com sucesso";
            }

        }
        return "Falha ao deletar";
    }

    @PostMapping("{id}/limpar/{quantidadeVidros}")
    public String limparVidro(@PathVariable int id, @PathVariable int quantidadeVidros){

        for (LimpaVidros l: limpaVidrosList) {
            if(l.getId() == id){
                return l.limparVidro(quantidadeVidros);
            }
        }

        return "Robô não cadastrado!";
    }

    @PostMapping("/recarregarBateria/{id}")
    public String recarregarBateria(@PathVariable int id){
        for (LimpaVidros l : limpaVidrosList) {
            if(l.getId() == id){
                return "Percentual carregado! " + l.recarregarEnergia();
            }
        }
        return "Robô não cadastrado!";
    }
}
