package com.lorenzon.aluno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lorenzon.aluno.model.Aluno;
import com.lorenzon.aluno.repository.AlunoRepositoryInterface;

@Service
public class AlunoService {

    @Qualifier("alunoPersistidoemNativoOO")
    AlunoRepositoryInterface alunorepositorio;

    public AlunoService(AlunoRepositoryInterface alunorepositorio) {
        this.alunorepositorio = alunorepositorio;
    }

    public String listarTodos() {
        List<Aluno> alunos = alunorepositorio.listar();

        if (alunos == null || alunos.isEmpty()) {
            return "Nenhum aluno cadastrado.";
        }

        StringBuilder saida = new StringBuilder();
        saida.append("Lista de alunos cadastrados:<br>");

        for (Aluno aluno : alunos) {
            if (aluno == null) {
                continue;
            }

            saida.append("CPF: ").append(aluno.getCpf()).append("\n");
            saida.append("Nome: ").append(aluno.getNome()).append("\n");
            saida.append("Idade: ").append(aluno.getIdade()).append("\n");
            saida.append("Matricula: ").append(aluno.getMatricula()).append("\n");
            saida.append("<br>");
        }

        return saida.toString();
    }
}
