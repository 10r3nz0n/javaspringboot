package com.lorenzon.aluno.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.lorenzon.aluno.domain.model.Disciplina;
import com.lorenzon.aluno.domain.repository.DisciplinaRepositoryInterface;

@Repository
public class DisciplinaRepositoryJpaAdapter implements DisciplinaRepositoryInterface {

    private final DisciplinaJpaRepository disciplinaJpaRepository;

    public DisciplinaRepositoryJpaAdapter(DisciplinaJpaRepository disciplinaJpaRepository) {
        this.disciplinaJpaRepository = disciplinaJpaRepository;
    }

    @Override
    public Disciplina save(Disciplina disciplina) {
        return disciplinaJpaRepository.save(disciplina);
    }

    @Override
    public List<Disciplina> findAll() {
        return disciplinaJpaRepository.findAll();
    }

    @Override
    public Optional<Disciplina> findById(Long id) {
        return disciplinaJpaRepository.findById(id);
    }

    @Override
    public Optional<Disciplina> findByCodigo(String codigo) {
        return disciplinaJpaRepository.findByCodigo(codigo);
    }

    @Override
    public void deleteById(Long id) {
        disciplinaJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return disciplinaJpaRepository.existsById(id);
    }
}
