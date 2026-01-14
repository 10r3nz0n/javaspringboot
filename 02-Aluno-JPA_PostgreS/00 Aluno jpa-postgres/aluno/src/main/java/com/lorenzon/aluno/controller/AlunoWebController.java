package com.lorenzon.aluno.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lorenzon.aluno.dto.AlunoForm;
import com.lorenzon.aluno.model.Aluno;
import com.lorenzon.aluno.routes.AlunoRoutesAdvice;
import com.lorenzon.aluno.service.AlunoWebService;

@Controller
@RequestMapping(AlunoRoutesAdvice.WEB_ALUNO)
public class AlunoWebController {

    private final AlunoWebService alunoWebService;

    public AlunoWebController(AlunoWebService alunoWebService) {
        this.alunoWebService = alunoWebService;
    }

    @GetMapping(AlunoRoutesAdvice.WEB_CRIAR)
    public String criar(Model model) {
        model.addAttribute("alunoForm", new AlunoForm());
        model.addAttribute("modoEdicao", false);
        return "aluno-form";
    }

    @PostMapping(AlunoRoutesAdvice.WEB_SALVAR)
    public String salvar(@ModelAttribute("alunoForm") AlunoForm alunoForm, Model model) {
        try {
            alunoWebService.cadastrar(alunoForm);
            return "redirect:" + AlunoRoutesAdvice.WEB_ALUNO_LISTAR;
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("modoEdicao", false);
            return "aluno-form";
        }
    }

    @GetMapping(AlunoRoutesAdvice.WEB_LISTAR)
    public String listar(Model model) {
        model.addAttribute("alunos", alunoWebService.listar());
        return "aluno-lista";
    }

    @GetMapping(AlunoRoutesAdvice.WEB_EDITAR + "/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        try {
            AlunoForm form = alunoWebService.carregarParaEdicao(id);
            model.addAttribute("alunoForm", form);
            model.addAttribute("modoEdicao", true);
            return "aluno-form";
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("alunos", alunoWebService.listar());
            return "aluno-lista";
        }
    }

    @PostMapping(AlunoRoutesAdvice.WEB_ATUALIZAR + "/{id}")
    public String atualizar(@PathVariable("id") Long id,
            @ModelAttribute("alunoForm") AlunoForm alunoForm,
            Model model) {
        try {
            alunoWebService.atualizar(id, alunoForm);
            return "redirect:" + AlunoRoutesAdvice.WEB_ALUNO_LISTAR;
        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("modoEdicao", true);
            return "aluno-form";
        }
    }

    @PostMapping(AlunoRoutesAdvice.WEB_EXCLUIR + "/{id}")
    public String excluir(@PathVariable("id") Long id) {
        alunoWebService.excluirPorId(id);
        return "redirect:" + AlunoRoutesAdvice.WEB_ALUNO_LISTAR;
    }

    @GetMapping(AlunoRoutesAdvice.WEB_BUSCAR)
    public String buscar(@RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "cpf", required = false) String cpf,
            @RequestParam(name = "matricula", required = false) String matricula,
            Model model) {

        List<Aluno> resultado = new ArrayList<>();

        Optional<Aluno> porId = alunoWebService.buscarPorId(id);
        if (porId.isPresent()) {
            resultado.add(porId.get());
        } else {
            Optional<Aluno> porCpf = alunoWebService.buscarPorCpf(cpf);
            if (porCpf.isPresent()) {
                resultado.add(porCpf.get());
            } else {
                Optional<Aluno> porMat = alunoWebService.buscarPorMatricula(matricula);
                porMat.ifPresent(resultado::add);
            }
        }

        model.addAttribute("alunos", resultado);
        return "aluno-lista";
    }
}
