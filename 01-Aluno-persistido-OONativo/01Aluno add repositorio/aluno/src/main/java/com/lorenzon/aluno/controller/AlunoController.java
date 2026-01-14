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

    @GetMapping("/listar")
    public String listarAlunos() {
        return alunoService.listarTodos();
    }

}
