package com.lorenzon.aluno.interfaces.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DisciplinaForm {

    @NotBlank(message = "Codigo obrigatorio.")
    @Size(max = 30, message = "Codigo deve ter no maximo 30 caracteres.")
    private String codigo;

    @NotBlank(message = "Descricao obrigatoria.")
    @Size(max = 200, message = "Descricao deve ter no maximo 200 caracteres.")
    private String descricao;

    public DisciplinaForm() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
