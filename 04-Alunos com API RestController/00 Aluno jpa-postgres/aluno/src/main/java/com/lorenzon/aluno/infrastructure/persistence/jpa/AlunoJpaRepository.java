package com.lorenzon.aluno.infrastructure.persistence.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorenzon.aluno.domain.model.Aluno;
import com.lorenzon.aluno.domain.model.Cpf;

/*
  Repositorio Spring Data JPA
  Pattern: Repository como detalhe do framework
*/

public interface AlunoJpaRepository extends JpaRepository<Aluno, Long> {

  Optional<Aluno> findByCpf(Cpf cpf);

  Optional<Aluno> findByMatricula(String matricula);
}
