package com.lorenzon.aluno.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lorenzon.aluno.dto.AlunoForm;
import com.lorenzon.aluno.model.Aluno;
import com.lorenzon.aluno.model.Cpf;
import com.lorenzon.aluno.repository.AlunoRepositoryInterface;

@Service
public class AlunoWebService {

    @Autowired
    @Qualifier("alunoPersistidoemNativoOO")
    AlunoRepositoryInterface alunoRepositorio;

    public void cadastrar(AlunoForm form) {
        if (form == null) {
            throw new IllegalArgumentException("Formulario nulo.");
        }
        if (form.getCpf() == null || form.getCpf().isBlank()) {
            throw new IllegalArgumentException("CPF obrigatorio.");
        }
        if (form.getNome() == null || form.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome obrigatorio.");
        }
        if (form.getIdade() == null) {
            throw new IllegalArgumentException("Idade obrigatoria.");
        }
        if (form.getMatricula() == null || form.getMatricula().isBlank()) {
            throw new IllegalArgumentException("Matricula obrigatoria.");
        }

        Cpf cpf = new Cpf(form.getCpf());

        Aluno aluno = new Aluno();
        aluno.setCpf(cpf);
        aluno.setNome(form.getNome());
        aluno.setIdade(form.getIdade());
        aluno.setMatricula(form.getMatricula());

        alunoRepositorio.salvar(aluno);
    }

    public List<Aluno> listar() {
        return alunoRepositorio.listar();
    }

}
