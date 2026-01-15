package com.lorenzon.aluno.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lorenzon.aluno.domain.model.Aluno;
import com.lorenzon.aluno.domain.model.Cpf;
import com.lorenzon.aluno.domain.repository.AlunoRepositoryInterface;

/*
NOTA DE ARQUITETURA
Camada: Application (Casos de uso)
Responsabilidade: Orquestrar regras, validacoes, transacoes e persistencia via portas.
Principios aplicados:
SOLID - SRP: implementa casos de uso, nao detalhes de HTTP nem detalhes JPA.
SOLID - DIP: depende de AlunoRepositoryInterface (abstracao), nao de Spring Data.
Arquitetura: ponto central reutilizado por Web e API.
DDD - Application Service: coordena dominio e repositorios, sem se tornar controlador.
Object calisthenics: metodos pequenos e guard clauses
Observacao: Validacao de duplicidade em cpf e matricula ocorre antes do banco para reduzir falhas.
*/

@Service
public class AlunoApplicationService {

    private final AlunoRepositoryInterface alunoRepository;

    public AlunoApplicationService(AlunoRepositoryInterface alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Aluno criar(Cpf cpf, String nome, int idade, String matricula) {
        Aluno aluno = new Aluno(cpf, nome, idade, matricula);
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno atualizar(Long id, Cpf cpf, String nome, int idade, String matricula) {
        if (id == null) {
            throw new IllegalArgumentException("Id obrigatorio.");
        }

        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno nao encontrado."));

        aluno.alterarCpf(cpf);
        aluno.alterarNome(nome);
        aluno.alterarIdade(idade);
        aluno.alterarMatricula(matricula);

        return alunoRepository.save(aluno);
    }

    public List<Aluno> listar() {
        return alunoRepository.findAll();
    }

    public Optional<Aluno> buscarPorId(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return alunoRepository.findById(id);
    }

    public Optional<Aluno> buscarPorCpf(String cpfTexto) {
        if (cpfTexto == null || cpfTexto.isBlank()) {
            return Optional.empty();
        }
        return alunoRepository.findByCpf(new Cpf(cpfTexto));
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        if (matricula == null || matricula.isBlank()) {
            return Optional.empty();
        }
        return alunoRepository.findByMatricula(matricula.trim());
    }

    public List<Aluno> buscar(Long id, String cpf, String matricula) {
        List<Aluno> resultado = new ArrayList<>();

        Optional<Aluno> porId = buscarPorId(id);
        if (porId.isPresent()) {
            resultado.add(porId.get());
            return resultado;
        }

        Optional<Aluno> porCpf = buscarPorCpf(cpf);
        if (porCpf.isPresent()) {
            resultado.add(porCpf.get());
            return resultado;
        }

        Optional<Aluno> porMatricula = buscarPorMatricula(matricula);
        porMatricula.ifPresent(resultado::add);

        return resultado;
    }

    @Transactional
    public void excluirPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id obrigatorio.");
        }
        if (!alunoRepository.existsById(id)) {
            throw new IllegalArgumentException("Aluno nao encontrado.");
        }
        alunoRepository.deleteById(id);
    }

    @Transactional
    public Aluno cadastrar(Aluno aluno) {
        validarAlunoObrigatorio(aluno);
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno atualizar(Long id, Aluno aluno) {
        if (id == null) {
            throw new IllegalArgumentException("Id obrigatorio.");
        }
        validarAlunoObrigatorio(aluno);

        Aluno existente = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno nao encontrado."));

        existente.setCpf(aluno.getCpf());
        existente.setNome(aluno.getNome());
        existente.setIdade(aluno.getIdade());
        existente.setMatricula(aluno.getMatricula());

        return alunoRepository.save(existente);
    }

    private void validarAlunoObrigatorio(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno nulo.");
        }
        if (aluno.getCpf() == null) {
            throw new IllegalArgumentException("CPF obrigatorio.");
        }
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome obrigatorio.");
        }
        if (aluno.getMatricula() == null || aluno.getMatricula().isBlank()) {
            throw new IllegalArgumentException("Matricula obrigatoria.");
        }
        aluno.setIdade(aluno.getIdade());
    }

}
