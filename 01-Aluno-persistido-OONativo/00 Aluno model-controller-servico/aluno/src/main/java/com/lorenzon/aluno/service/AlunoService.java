package com.lorenzon.aluno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lorenzon.aluno.model.Aluno;
import com.lorenzon.aluno.model.Cpf;
import com.lorenzon.aluno.repository.AlunoRepository;

@Service
public class AlunoService {

    // Sem repositório injetado ainda

    public String criaExemploAlunoRetorna() {

        // Aqui ainda não temos um repositorio, logo vamos criar na mão um aluno pra
        // apresentá-lo

        Aluno aluno = new Aluno(new Cpf("71688684972"), "Lorenzon", 54, "10555");
        return aluno.toString();
    }

}
