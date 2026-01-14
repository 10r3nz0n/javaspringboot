package com.lorenzon.aluno.routes;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class AlunoRoutesAdvice {

    // Web MVC
    public static final String WEB_ALUNO = "/web/aluno";
    public static final String WEB_CRIAR = "/criar";
    public static final String WEB_SALVAR = "/salvar";
    public static final String WEB_LISTAR = "/listar";
    public static final String WEB_EDITAR = "/editar";
    public static final String WEB_ATUALIZAR = "/atualizar";
    public static final String WEB_EXCLUIR = "/excluir";
    public static final String WEB_BUSCAR = "/buscar";

    public static final String WEB_ALUNO_CRIAR = WEB_ALUNO + WEB_CRIAR;
    public static final String WEB_ALUNO_SALVAR = WEB_ALUNO + WEB_SALVAR;
    public static final String WEB_ALUNO_LISTAR = WEB_ALUNO + WEB_LISTAR;

    // Expondo para Thymeleaf
    @ModelAttribute("routes")
    public AlunoRoutesAdvice routes() {
        return this;
    }

    // Getters para Thymeleaf
    public String getWebAlunoCriar() {
        return WEB_ALUNO_CRIAR;
    }

    public String getWebAlunoSalvar() {
        return WEB_ALUNO_SALVAR;
    }

    public String getWebAlunoListar() {
        return WEB_ALUNO_LISTAR;
    }

    public String getWebAlunoBuscar() {
        return WEB_ALUNO + WEB_BUSCAR;
    }

    public String getWebAlunoEditar() {
        return WEB_ALUNO + WEB_EDITAR;
    }

    public String getWebAlunoAtualizar() {
        return WEB_ALUNO + WEB_ATUALIZAR;
    }

    public String getWebAlunoExcluir() {
        return WEB_ALUNO + WEB_EXCLUIR;
    }
}
