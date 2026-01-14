package com.lorenzon.aluno.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzon.aluno.model.Aluno;
import com.lorenzon.aluno.model.Cpf;
import com.lorenzon.aluno.service.AlunoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/aluno")

public class AlunoController {

    @Autowired
    AlunoService alunoService;

    /*
     * Lembrando, o controller é a porta de entrada de uma requisição, é resolvido o
     * host:porta e os caminhos e o verbo:
     * Exemplo para o caso da seguinte requisicao:
     * http://localhost:3000/aluno/autocriaemostra
     * 
     * http://localhost:3000, o Spring MVC/DispatcherServlet orquestra a requisição
     * no servidor embutido
     * /aluno é resulvido em @RequestMapping("/aluno")
     * /autocriaemostra é resolvido com o verbo get
     * em @GetMapping("/autocriaemostra")
     */

    @GetMapping("/autocriaemostra")
    public String respostaAlunoAutoCriaEMostra() {
        return alunoService.criaExemploAlunoRetorna();
    }

}
