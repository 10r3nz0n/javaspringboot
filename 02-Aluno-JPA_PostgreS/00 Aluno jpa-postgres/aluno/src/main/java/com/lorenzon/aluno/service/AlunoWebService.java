package com.lorenzon.aluno.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lorenzon.aluno.dto.AlunoForm;
import com.lorenzon.aluno.model.Aluno;
import com.lorenzon.aluno.model.Cpf;
import com.lorenzon.aluno.repository.AlunoRepositoryInterface;

@Service
public class AlunoWebService {

    private final AlunoRepositoryInterface alunoRepositorio;

    public AlunoWebService(AlunoRepositoryInterface alunoRepositorio) {
        this.alunoRepositorio = alunoRepositorio;
    }

    @Transactional
    public Aluno cadastrar(AlunoForm form) {
        validarFormObrigatorio(form);

        Cpf cpf = new Cpf(form.getCpf());

        Aluno aluno = new Aluno();
        aluno.setCpf(cpf);
        aluno.setNome(form.getNome());
        aluno.setIdade(form.getIdade());
        aluno.setMatricula(form.getMatricula());

        return alunoRepositorio.save(aluno);
    }

    @Transactional
    public Aluno atualizar(Long id, AlunoForm form) {
        if (id == null) {
            throw new IllegalArgumentException("Id obrigatorio.");
        }
        validarFormObrigatorio(form);

        Aluno aluno = alunoRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno nao encontrado."));

        aluno.setCpf(new Cpf(form.getCpf()));
        aluno.setNome(form.getNome());
        aluno.setIdade(form.getIdade());
        aluno.setMatricula(form.getMatricula());

        return alunoRepositorio.save(aluno);
    }

    public List<Aluno> listar() {
        return alunoRepositorio.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return alunoRepositorio.findById(id);
    }

    public Optional<Aluno> buscarPorCpf(String cpfTexto) {
        if (cpfTexto == null || cpfTexto.isBlank()) {
            return Optional.empty();
        }
        Cpf cpf = new Cpf(cpfTexto);
        return alunoRepositorio.findByCpf(cpf);
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        if (matricula == null || matricula.isBlank()) {
            return Optional.empty();
        }
        return alunoRepositorio.findByMatricula(matricula.trim());
    }

    @Transactional
    public void excluirPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id obrigatorio.");
        }
        if (!alunoRepositorio.existsById(id)) {
            throw new IllegalArgumentException("Aluno nao encontrado.");
        }
        alunoRepositorio.deleteById(id);
    }

    @Transactional
    public void excluirPorCpf(String cpfTexto) {
        Aluno aluno = buscarPorCpf(cpfTexto)
                .orElseThrow(() -> new IllegalArgumentException("Aluno nao encontrado."));
        alunoRepositorio.deleteById(aluno.getId());
    }

    @Transactional
    public void excluirPorMatricula(String matricula) {
        Aluno aluno = buscarPorMatricula(matricula)
                .orElseThrow(() -> new IllegalArgumentException("Aluno nao encontrado."));
        alunoRepositorio.deleteById(aluno.getId());
    }

    public AlunoForm carregarParaEdicao(Long id) {
        Aluno aluno = alunoRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno nao encontrado."));

        AlunoForm form = new AlunoForm();
        form.setId(aluno.getId());
        form.setCpf(aluno.getCpf() != null ? aluno.getCpf().getNumero() : "");
        form.setNome(aluno.getNome());
        form.setIdade(aluno.getIdade());
        form.setMatricula(aluno.getMatricula());
        return form;
    }

    private void validarFormObrigatorio(AlunoForm form) {
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
    }
}
