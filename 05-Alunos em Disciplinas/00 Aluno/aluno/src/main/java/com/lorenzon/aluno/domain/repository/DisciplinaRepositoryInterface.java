package com.lorenzon.aluno.domain.repository;

import java.util.List;
import java.util.Optional;

import com.lorenzon.aluno.domain.model.Disciplina;

public interface DisciplinaRepositoryInterface {

    Disciplina save(Disciplina disciplina);

    List<Disciplina> findAll();

    Optional<Disciplina> findById(Long id);

    Optional<Disciplina> findByCodigo(String codigo);

    void deleteById(Long id);

    boolean existsById(Long id);
}
