package com.lorenzon.aluno.interfaces.web.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.lorenzon.aluno.application.service.AlunoApplicationService;
import com.lorenzon.aluno.application.service.DisciplinaApplicationService;
import com.lorenzon.aluno.domain.model.Disciplina;
import com.lorenzon.aluno.infrastructure.web.routes.RoutesAdvice;
import com.lorenzon.aluno.interfaces.web.dto.DisciplinaForm;
import com.lorenzon.aluno.interfaces.web.mapper.DisciplinaFormMapper;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(RoutesAdvice.WEB_DISCIPLINA)
public class DisciplinaWebController {

    private final DisciplinaApplicationService disciplinaService;
    private final AlunoApplicationService alunoService;
    private final DisciplinaFormMapper mapper;

    public DisciplinaWebController(
            DisciplinaApplicationService disciplinaService,
            AlunoApplicationService alunoService) {
        this.disciplinaService = disciplinaService;
        this.alunoService = alunoService;
        this.mapper = new DisciplinaFormMapper();
    }

    @GetMapping(RoutesAdvice.WEB_CRIAR)
    public String criar(Model model) {
        model.addAttribute("disciplinaForm", new DisciplinaForm());
        model.addAttribute("modoEdicao", false);
        return "disciplina-form";
    }

    @PostMapping(RoutesAdvice.WEB_SALVAR)
    public String salvar(
            @Valid @ModelAttribute("disciplinaForm") DisciplinaForm form,
            BindingResult binding,
            Model model) {

        if (binding.hasErrors()) {
            model.addAttribute("modoEdicao", false);
            return "disciplina-form";
        }

        try {
            disciplinaService.criar(form.getCodigo(), form.getDescricao());
            return "redirect:" + RoutesAdvice.WEB_DISCIPLINA_LISTAR;
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("modoEdicao", false);
            return "disciplina-form";
        }
    }

    @GetMapping(RoutesAdvice.WEB_LISTAR)
    public String listar(Model model) {
        model.addAttribute("disciplinas", disciplinaService.listar());
        return "disciplina-lista";
    }

    @GetMapping(RoutesAdvice.WEB_EDITAR + "/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        return disciplinaService.buscarPorId(id)
                .map(d -> {
                    model.addAttribute("disciplinaForm", mapper.toForm(d));
                    model.addAttribute("modoEdicao", true);
                    model.addAttribute("id", d.getId());
                    return "disciplina-form";
                })
                .orElseGet(() -> {
                    model.addAttribute("erro", "Disciplina nao encontrada.");
                    model.addAttribute("disciplinas", disciplinaService.listar());
                    return "disciplina-lista";
                });
    }

    @PostMapping(RoutesAdvice.WEB_ATUALIZAR + "/{id}")
    public String atualizar(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("disciplinaForm") DisciplinaForm form,
            BindingResult binding,
            Model model) {

        if (binding.hasErrors()) {
            model.addAttribute("modoEdicao", true);
            model.addAttribute("id", id);
            return "disciplina-form";
        }

        try {
            disciplinaService.atualizar(id, form.getCodigo(), form.getDescricao());
            return "redirect:" + RoutesAdvice.WEB_DISCIPLINA_LISTAR;
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("modoEdicao", true);
            model.addAttribute("id", id);
            return "disciplina-form";
        }
    }

    @PostMapping(RoutesAdvice.WEB_EXCLUIR + "/{id}")
    public String excluir(@PathVariable("id") Long id) {
        disciplinaService.excluir(id);
        return "redirect:" + RoutesAdvice.WEB_DISCIPLINA_LISTAR;
    }

    @GetMapping(RoutesAdvice.WEB_MATRICULAS + "/{id}")
    public String matriculas(@PathVariable("id") Long id, Model model) {
        Disciplina disciplina = disciplinaService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina nao encontrada."));

        Set<Long> matriculadosIds = new HashSet<>();
        if (disciplina.getAlunos() != null) {
            disciplina.getAlunos().forEach(a -> matriculadosIds.add(a.getId()));
        }

        model.addAttribute("disciplina", disciplina);
        model.addAttribute("alunos", alunoService.listar());
        model.addAttribute("matriculadosIds", matriculadosIds);
        return "disciplina-matriculas";
    }

    @PostMapping(RoutesAdvice.WEB_MATRICULAS + "/{id}")
    public String atualizarMatriculas(
            @PathVariable("id") Long id,
            @RequestParam(value = "alunoIds", required = false) List<Long> alunoIds) {

        disciplinaService.atualizarMatriculas(id, alunoIds);
        return "redirect:" + RoutesAdvice.WEB_DISCIPLINA_LISTAR;
    }
}
