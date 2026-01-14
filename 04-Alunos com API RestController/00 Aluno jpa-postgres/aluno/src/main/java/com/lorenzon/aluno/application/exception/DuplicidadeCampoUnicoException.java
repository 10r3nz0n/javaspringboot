package com.lorenzon.aluno.application.exception;

import java.util.Collections;
import java.util.List;

/*
NOTA DE ARQUITETURA
Camada: Application (Erro previsivel)
Responsabilidade: Representar conflito de negocio para campos unicos (cpf, matricula).
Principios aplicados:
SOLID - SRP: excecao com dados minimos para diagnostico.
Arquitetura: permite que a camada Interfaces converta em 409 Conflict de forma limpa.
DDD: sinaliza violacao de regra de consistencia observada pelo caso de uso.
*/

public class DuplicidadeCampoUnicoException extends RuntimeException {

    private final String campo;
    private final List<String> valores;

    public DuplicidadeCampoUnicoException(String campo, List<String> valores) {
        super("Duplicidade detectada no campo: " + campo);
        this.campo = campo;
        this.valores = valores == null ? Collections.emptyList() : Collections.unmodifiableList(valores);
    }

    public String getCampo() {
        return campo;
    }

    public List<String> getValores() {
        return valores;
    }
}
