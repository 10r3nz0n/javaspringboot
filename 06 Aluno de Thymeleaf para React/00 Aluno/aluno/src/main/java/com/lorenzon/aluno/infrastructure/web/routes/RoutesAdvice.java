package com.lorenzon.aluno.infrastructure.web.routes;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/*
  Adaptador web de suporte
  SOLID SRP: rotas e exposicao no Model para Thymeleaf
  Observacao: remove strings hardcoded dos templates
*/

@ControllerAdvice
public class RoutesAdvice {

    // HOME
    public static final String WEB_HOME = "/web/home";
    public static final String WEB_HOME_INDEX = "/";

    // WEB (Thymeleaf)
    public static final String WEB_ALUNO = "/web/aluno";
    public static final String WEB_DISCIPLINA = "/web/disciplina";

    public static final String WEB_CRIAR = "/criar";
    public static final String WEB_SALVAR = "/salvar";
    public static final String WEB_LISTAR = "/listar";
    public static final String WEB_BUSCAR = "/buscar";
    public static final String WEB_EDITAR = "/editar";
    public static final String WEB_ATUALIZAR = "/atualizar";
    public static final String WEB_EXCLUIR = "/excluir";

    // ALUNO
    public static final String WEB_ALUNO_CRIAR = WEB_ALUNO + WEB_CRIAR;
    public static final String WEB_ALUNO_SALVAR = WEB_ALUNO + WEB_SALVAR;
    public static final String WEB_ALUNO_LISTAR = WEB_ALUNO + WEB_LISTAR;
    public static final String WEB_ALUNO_BUSCAR = WEB_ALUNO + WEB_BUSCAR;
    public static final String WEB_ALUNO_EDITAR = WEB_ALUNO + WEB_EDITAR;
    public static final String WEB_ALUNO_ATUALIZAR = WEB_ALUNO + WEB_ATUALIZAR;
    public static final String WEB_ALUNO_EXCLUIR = WEB_ALUNO + WEB_EXCLUIR;

    // DISCIPLINA (preparado para quando tu incluir Disciplina no projeto)
    public static final String WEB_DISCIPLINA_CRIAR = WEB_DISCIPLINA + WEB_CRIAR;
    public static final String WEB_DISCIPLINA_SALVAR = WEB_DISCIPLINA + WEB_SALVAR;
    public static final String WEB_DISCIPLINA_LISTAR = WEB_DISCIPLINA + WEB_LISTAR;
    public static final String WEB_DISCIPLINA_EDITAR = WEB_DISCIPLINA + WEB_EDITAR;
    public static final String WEB_DISCIPLINA_ATUALIZAR = WEB_DISCIPLINA + WEB_ATUALIZAR;
    public static final String WEB_DISCIPLINA_EXCLUIR = WEB_DISCIPLINA + WEB_EXCLUIR;

    public static final String WEB_MATRICULAS = "/matriculas";
    public static final String WEB_DISCIPLINA_MATRICULAS = WEB_DISCIPLINA + WEB_MATRICULAS;

    // API (REST JSON)
    public static final String API_ALUNOS = "/api/alunos";
    public static final String API_BATCH = "/batch";
    public static final String API_POR_ID = "/{id}";
    public static final String API_POR_CPF = "/cpf/{cpf}";
    public static final String API_POR_MATRICULA = "/matricula/{matricula}";

    @ModelAttribute("routes")
    public RoutesAdvice routes() {
        return this;
    }

    public String getWebHome() {
        return WEB_HOME;
    }

    public String getWebHomeIndex() {
        return WEB_HOME_INDEX;
    }

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
        return WEB_ALUNO_BUSCAR;
    }

    public String getWebAlunoEditar() {
        return WEB_ALUNO_EDITAR;
    }

    public String getWebAlunoAtualizar() {
        return WEB_ALUNO_ATUALIZAR;
    }

    public String getWebAlunoExcluir() {
        return WEB_ALUNO_EXCLUIR;
    }

    public String getWebDisciplinaCriar() {
        return WEB_DISCIPLINA_CRIAR;
    }

    public String getWebDisciplinaSalvar() {
        return WEB_DISCIPLINA_SALVAR;
    }

    public String getWebDisciplinaListar() {
        return WEB_DISCIPLINA_LISTAR;
    }

    public String getWebDisciplinaEditar() {
        return WEB_DISCIPLINA_EDITAR;
    }

    public String getWebDisciplinaAtualizar() {
        return WEB_DISCIPLINA_ATUALIZAR;
    }

    public String getWebDisciplinaExcluir() {
        return WEB_DISCIPLINA_EXCLUIR;
    }

    public String getWebDisciplinaMatriculas() {
        return WEB_DISCIPLINA_MATRICULAS;
    }
}
