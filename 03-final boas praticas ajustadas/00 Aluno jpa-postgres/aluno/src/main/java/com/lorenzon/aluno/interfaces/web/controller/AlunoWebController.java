package com.lorenzon.aluno.interfaces.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.lorenzon.aluno.application.service.AlunoApplicationService;
import com.lorenzon.aluno.domain.model.Aluno;
import com.lorenzon.aluno.domain.model.Cpf;
import com.lorenzon.aluno.infrastructure.web.routes.RoutesAdvice;
import com.lorenzon.aluno.interfaces.web.dto.AlunoForm;
import com.lorenzon.aluno.interfaces.web.mapper.AlunoFormMapper;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/*
  Controller web MVC
  SOLID SRP: orquestracao http e view apenas
  DDD: chama camada application, sem detalhes de persistencia aqui
*/

@Controller
@RequestMapping(RoutesAdvice.WEB_ALUNO)
public class AlunoWebController {

    private final AlunoApplicationService alunoService;
    private final AlunoFormMapper mapper;

    public AlunoWebController(AlunoApplicationService alunoService) {
        this.alunoService = alunoService;
        this.mapper = new AlunoFormMapper();
    }

    @GetMapping(RoutesAdvice.WEB_CRIAR)
    public String criar(Model model) {
        model.addAttribute("alunoForm", new AlunoForm());
        model.addAttribute("modoEdicao", false);
        return "aluno-form";
    }

    @PostMapping(RoutesAdvice.WEB_SALVAR)
    public String salvar(@Valid @ModelAttribute("alunoForm") AlunoForm form,
            BindingResult binding,
            Model model) {

        if (binding.hasErrors()) {
            model.addAttribute("modoEdicao", false);
            return "aluno-form";
        }

        try {
            alunoService.criar(new Cpf(form.getCpf()), form.getNome(), form.getIdade(), form.getMatricula());
            return "redirect:" + RoutesAdvice.WEB_ALUNO_LISTAR;
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("modoEdicao", false);
            return "aluno-form";
        }
    }

    @GetMapping(RoutesAdvice.WEB_LISTAR)
    public String listar(Model model) {
        model.addAttribute("alunos", alunoService.listar());
        return "aluno-lista";
    }

    @GetMapping(RoutesAdvice.WEB_EDITAR + "/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        return alunoService.buscarPorId(id)
                .map(aluno -> {
                    model.addAttribute("alunoForm", mapper.toForm(aluno));
                    model.addAttribute("modoEdicao", true);
                    return "aluno-form";
                })
                .orElseGet(() -> {
                    model.addAttribute("erro", "Aluno nao encontrado.");
                    model.addAttribute("alunos", alunoService.listar());
                    return "aluno-lista";
                });
    }

    @PostMapping(RoutesAdvice.WEB_ATUALIZAR + "/{id}")
    public String atualizar(@PathVariable("id") Long id,
            @Valid @ModelAttribute("alunoForm") AlunoForm form,
            BindingResult binding,
            Model model) {

        if (binding.hasErrors()) {
            model.addAttribute("modoEdicao", true);
            return "aluno-form";
        }

        try {
            alunoService.atualizar(id, new Cpf(form.getCpf()), form.getNome(), form.getIdade(), form.getMatricula());
            return "redirect:" + RoutesAdvice.WEB_ALUNO_LISTAR;
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("modoEdicao", true);
            return "aluno-form";
        }
    }

    @PostMapping(RoutesAdvice.WEB_EXCLUIR + "/{id}")
    public String excluir(@PathVariable("id") Long id) {
        alunoService.excluirPorId(id);
        return "redirect:" + RoutesAdvice.WEB_ALUNO_LISTAR;
    }

    @GetMapping(RoutesAdvice.WEB_BUSCAR)
    public String buscar(@RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "cpf", required = false) String cpf,
            @RequestParam(name = "matricula", required = false) String matricula,
            Model model) {

        List<Aluno> alunos = alunoService.buscar(id, cpf, matricula);
        model.addAttribute("alunos", alunos);
        return "aluno-lista";
    }
}
