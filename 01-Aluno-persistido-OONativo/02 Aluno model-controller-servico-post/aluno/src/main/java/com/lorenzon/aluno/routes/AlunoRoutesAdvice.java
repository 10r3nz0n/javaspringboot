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

    public static final String WEB_ALUNO_CRIAR = WEB_ALUNO + WEB_CRIAR;
    public static final String WEB_ALUNO_SALVAR = WEB_ALUNO + WEB_SALVAR;
    public static final String WEB_ALUNO_LISTAR = WEB_ALUNO + WEB_LISTAR;

    // REST API
    public static final String API_ALUNO = "/api/aluno";
    public static final String API_LISTAR = "/listar";
    public static final String API_SALVAR = "/salvar";
    public static final String API_AUTOCRIA_E_MOSTRA = "/autocriaemostra";

    public static final String API_ALUNO_LISTAR = API_ALUNO + API_LISTAR;
    public static final String API_ALUNO_SALVAR = API_ALUNO + API_SALVAR;
    public static final String API_ALUNO_AUTOCRIA_E_MOSTRA = API_ALUNO + API_AUTOCRIA_E_MOSTRA;

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

    public String getApiAlunoListar() {
        return API_ALUNO_LISTAR;
    }

    public String getApiAlunoSalvar() {
        return API_ALUNO_SALVAR;
    }

    public String getApiAlunoAutoCriaEMostra() {
        return API_ALUNO_AUTOCRIA_E_MOSTRA;
    }
}
