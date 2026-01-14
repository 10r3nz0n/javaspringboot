package com.lorenzon.aluno.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lorenzon.aluno.dto.AlunoForm;
import com.lorenzon.aluno.model.Aluno;
import com.lorenzon.aluno.routes.AlunoRoutesAdvice;
import com.lorenzon.aluno.service.AlunoWebService;

import lombok.AllArgsConstructor;

/*
   @Controller e @RestController (@Controller + @ResponseBody) são dois modos diferentes do Spring MVC entregar uma resposta HTTP. 
   Um serve páginas (view) e o outro serve dados (body). 
   Você pode operar só com Web Controller, sem REST, se seu objetivo atual é uma aplicação server side com formulários e páginas HTML.

   se o endpoint trabalha com representações no body, tipicamente JSON, e devolve dados no body, você está no território de @RestController. 
   Se o endpoint trabalha com páginas HTML e retorna templates, você está no território de @Controller. Só cuide de dois detalhes: 
   um @Controller pode devolver JSON se você usar @ResponseBody, e um 
   @RestController pode devolver texto puro. O critério real é “view versus body”.


   Controller MVC, @Controller
   Objetivo: servir HTML renderizado no servidor.
   Entrada típica: parâmetros de form, query string.
   Saída típica: nome de template e Model.

   Controller REST, @RestController
   Objetivo: servir uma API, dados no body.
   Entrada típica: @RequestBody com JSON (às vezes XML).
   Saída típica: objetos serializados em JSON, ResponseEntity, status codes.
*/

@Controller
@AllArgsConstructor
@RequestMapping(AlunoRoutesAdvice.WEB_ALUNO)

public class AlunoWebController {

    @Autowired
    private final AlunoWebService alunoWebService;

    @GetMapping(AlunoRoutesAdvice.WEB_CRIAR)
    public String novo(Model model) {
        model.addAttribute("alunoForm", new AlunoForm());
        return "aluno-form";
    }

    @PostMapping(AlunoRoutesAdvice.WEB_SALVAR)
    public String salvar(@ModelAttribute("alunoForm") AlunoForm alunoForm, Model model) {
        try {
            alunoWebService.cadastrar(alunoForm);
            return "redirect:/web/aluno/listar";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            return "aluno-form";
        }
    }

    @GetMapping(AlunoRoutesAdvice.WEB_LISTAR)
    public String lista(Model model) {
        List<Aluno> alunos = alunoWebService.listar();
        model.addAttribute("alunos", alunos);
        return "aluno-lista";
    }

}