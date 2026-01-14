package com.lorenzon.aluno.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Aluno implements Serializable { // Para então agora a persistência

    @Setter
    private Cpf cpf; // Value Object

    @Setter
    private String nome;

    private int idade; // não terá @Setter

    @Setter
    private String matricula;

    public void setIdade(int idade) {

        if (idade < 0) {
            throw new IllegalArgumentException("Idade invalida.");
        }

        this.idade = idade;

    }

    public void alterarIdade(int novaIdade) {
        if (novaIdade < 0) {
            throw new IllegalArgumentException("Idade invalida");
        }
        this.idade = novaIdade;
    }

}
