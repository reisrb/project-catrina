package com.project.catrina.controller;

import com.project.catrina.model.Aspirador;
import com.project.catrina.model.GerenciadorDeAgenda;
import com.project.catrina.model.Robo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/roboAspirador")
public class RoboAspiradorController {

    List<Robo> aspiradorList = new ArrayList<>();

    @GetMapping
    public List<Robo> getAspiradorList() {
        Robo r1 = new Aspirador(1, "robson", 5.2, 100, 5);

        if (aspiradorList.isEmpty()) {
            aspiradorList.add(r1);
        }

        return aspiradorList;
    }

    @PostMapping("/cadastrarRobo")
    public String adicionarRobo(@RequestBody Aspirador aspirador){

        if(aspiradorList.isEmpty()){
            aspiradorList.add(aspirador);
            return "robô adicionado";
        }else{
            for (Robo r : aspiradorList) {
                if( aspirador.getId() != r.getId()){
                    aspiradorList.add(aspirador);
                    return "robô adicionado";
                }
            }
        }

        for (Robo r: aspiradorList) {
            if( aspirador.getId() == r.getId()){
                return "Essa identificação de robô já está em nossa base de dados como:\n" + r;
            }
        }

        return "Houve uma falha nos dados!";
    }

    @PutMapping("/alterar/{id}")
    public String alterarRobo(@RequestBody Aspirador aspirador, @PathVariable int id){

        for (Robo r : aspiradorList) {
            if(r.getId() == id){
                aspiradorList.set(aspiradorList.indexOf(r), aspirador);
                return "Robô alterado com sucesso! Dados antigos: \n" + r;
            }
        }

        return "Id do robô não existe!";
    }

    @DeleteMapping("/deletar/{id}")
    public String deletarRobo(@PathVariable int id){

        for (Robo r : aspiradorList) {
            if(r.getId() == id){
                aspiradorList.remove(aspiradorList.indexOf(r));
                return "Robo deletado com sucesso";
            }

        }
        return "Falha ao deletar";
    }

    @PostMapping("{id}/aspirar/{horasLigado}")
    public String aspirarCasa(@PathVariable int horasLigado, @PathVariable int id){
        for (Robo r: aspiradorList) {
            if(r.getId() == id){
                return ((Aspirador) r).aspirar(horasLigado);
            }
        }

        return "Robô não cadastrado!";
    }

    @PostMapping("/recarregarBateria/{id}")
    public String recarregarBateria(@PathVariable int id){
        for (Robo r : aspiradorList) {
            if(r.getId() == id){
                return "Percentual carregado! " + r.recarregarEnergia();
            }
        }
        return "Robô não cadastrado!";
    }


}
