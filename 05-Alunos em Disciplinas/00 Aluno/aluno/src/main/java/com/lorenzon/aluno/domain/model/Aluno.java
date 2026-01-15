package com.lorenzon.aluno.domain.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
  DDD: Aggregate Root (Aluno)
  SOLID SRP: invariantes de dominio ficam aqui
  Object calisthenics: guard clauses e metodos com intencao
  Observacao: manter @Entity no dominio e um compromisso pragmatico para simplificar mapeamento
*/

@Entity
@Table(name = "aluno")

@Getter
@Setter
@AllArgsConstructor

public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private Cpf cpf;

    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "idade", nullable = false)
    private int idade;

    @Column(name = "matricula", nullable = false, unique = true, length = 30)
    private String matricula;

    public Aluno() {
        // JPA
    }

    public Aluno(Cpf cpf, String nome, int idade, String matricula) {
        alterarCpf(cpf);
        alterarNome(nome);
        alterarIdade(idade);
        alterarMatricula(matricula);
    }

    public Long getId() {
        return id;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getMatricula() {
        return matricula;
    }

    public void alterarCpf(Cpf cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF obrigatorio.");
        }
        this.cpf = cpf;
    }

    public void alterarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatorio.");
        }
        this.nome = nome.trim();
    }

    public void alterarIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade invalida.");
        }
        this.idade = idade;
    }

    public void alterarMatricula(String matricula) {
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matricula obrigatoria.");
        }
        this.matricula = matricula.trim();
    }
}
