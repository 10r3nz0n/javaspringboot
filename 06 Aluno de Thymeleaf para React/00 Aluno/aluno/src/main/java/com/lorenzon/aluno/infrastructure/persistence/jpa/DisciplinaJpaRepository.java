package com.lorenzon.aluno.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorenzon.aluno.domain.model.Disciplina;

public interface DisciplinaJpaRepository extends JpaRepository<Disciplina, Long> {

    Optional<Disciplina> findByCodigo(String codigo);
}
