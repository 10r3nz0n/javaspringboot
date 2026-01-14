package com.lorenzon.aluno.interfaces.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*
  DTO de entrada para web
  SOLID SRP: contrato de entrada apenas
  Observacao: Bean Validation deixa o controller mais limpo
*/
public class AlunoForm {

    private Long id;

    @NotBlank(message = "CPF obrigatorio.")
    private String cpf;

    @NotBlank(message = "Nome obrigatorio.")
    private String nome;

    @NotNull(message = "Idade obrigatoria.")
    @Min(value = 0, message = "Idade invalida.")
    private Integer idade;

    @NotBlank(message = "Matricula obrigatoria.")
    private String matricula;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
