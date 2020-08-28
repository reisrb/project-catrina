package com.project.catrina.controller;

import com.project.catrina.model.Aspirador;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roboAspirador")
public class RoboAspiradorController {

    List<Aspirador> aspiradorList = new ArrayList<>();

    @GetMapping
    public List<Aspirador> getAspiradorList() {
        return aspiradorList;
    }

    @PostMapping("/cadastrarRobo")
    public String adicionarRobo(@RequestBody Aspirador aspirador){

        if(aspiradorList.isEmpty()){
            aspiradorList.add(aspirador);
            return "robô adicionado";
        }else{
            for (Aspirador a : aspiradorList) {
                if( aspirador.getId() != a.getId()){
                    aspiradorList.add(aspirador);
                    return "robô adicionado";
                }
            }
        }

        for (Aspirador a: aspiradorList) {
            if( aspirador.getId() == a.getId()){
                return "Essa identificação de robô já está em nossa base de dados como:\n" + a;
            }
        }

        return "Houve uma falha nos dados!";
    }

    @PutMapping("/alterar/{id}")
    public String alterarRobo(@RequestBody Aspirador aspirador, @PathVariable int id){

        for (Aspirador a: aspiradorList) {
            if(a.getId() == id){
                aspiradorList.set(aspiradorList.indexOf(a), aspirador);
                return "Robô alterado com sucesso! Dados antigos: \n" + a;
            }
        }

        return "Id do robô não existe!";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarRobo(@PathVariable int id){

        for (Aspirador a : aspiradorList) {
            if(a.getId() == id){
                aspiradorList.remove(aspiradorList.indexOf(a));
                return "Robo deletado com sucesso";
            }

        }
        return "Falha ao deletar";
    }

    @PostMapping("{id}/aspirar/{horasLigado}")
    public String aspirarCasa(@PathVariable int horasLigado, @PathVariable int id){
        for (Aspirador a: aspiradorList) {
            if(a.getId() == id){
                return a.aspirar(horasLigado);
            }
        }

        return "Robô não cadastrado!";
    }

    @PostMapping("/recarregarBateria/{id}")
    public String recarregarBateria(@PathVariable int id){
        for (Aspirador a : aspiradorList) {
            if(a.getId() == id){
                return "Percentual carregado! " + a.recarregarEnergia();
            }
        }
        return "Robô não cadastrado!";
    }


}
