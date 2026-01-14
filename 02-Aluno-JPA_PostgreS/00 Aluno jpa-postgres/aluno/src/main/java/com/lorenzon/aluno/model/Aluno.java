package com.lorenzon.aluno.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "aluno")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private Cpf cpf;

    @Setter
    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    @Column(name = "idade", nullable = false)
    private int idade;

    @Setter
    @Column(name = "matricula", nullable = false, unique = true, length = 30)
    private String matricula;

    public void setIdade(int idade) {
        if (idade < 0) {
            throw new IllegalArgumentException("Idade invalida.");
        }
        this.idade = idade;
    }
}
