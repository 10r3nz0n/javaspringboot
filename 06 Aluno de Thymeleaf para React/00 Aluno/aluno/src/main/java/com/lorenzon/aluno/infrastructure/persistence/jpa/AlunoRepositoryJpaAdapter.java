package com.lorenzon.aluno.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lorenzon.aluno.domain.model.Aluno;
import com.lorenzon.aluno.domain.model.Cpf;
import com.lorenzon.aluno.domain.repository.AlunoRepositoryInterface;

/*
  Pattern: Adapter
  SOLID DIP: liga a porta do dominio na implementacao do Spring Data
*/

@Repository
public class AlunoRepositoryJpaAdapter implements AlunoRepositoryInterface {

    private final AlunoJpaRepository alunoJpaRepository;

    public AlunoRepositoryJpaAdapter(AlunoJpaRepository alunoJpaRepository) {
        this.alunoJpaRepository = alunoJpaRepository;
    }

    @Override
    public Aluno save(Aluno aluno) {
        return alunoJpaRepository.save(aluno);
    }

    @Override
    public List<Aluno> findAll() {
        return alunoJpaRepository.findAll();
    }

    @Override
    public Optional<Aluno> findById(Long id) {
        return alunoJpaRepository.findById(id);
    }

    @Override
    public Optional<Aluno> findByCpf(Cpf cpf) {
        return alunoJpaRepository.findByCpf(cpf);
    }

    @Override
    public Optional<Aluno> findByMatricula(String matricula) {
        return alunoJpaRepository.findByMatricula(matricula);
    }

    @Override
    public void deleteById(Long id) {
        alunoJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return alunoJpaRepository.existsById(id);
    }
}
