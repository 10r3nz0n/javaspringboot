package com.lorenzon.aluno.interfaces.api.mapper;

import com.lorenzon.aluno.domain.model.Aluno;
import com.lorenzon.aluno.domain.model.Cpf;
import com.lorenzon.aluno.interfaces.api.dto.AlunoRequestDto;
import com.lorenzon.aluno.interfaces.api.dto.AlunoResponseDto;

/*
NOTA DE ARQUITETURA
Camada: Interfaces (Traducao)
Responsabilidade: Converter DTOs da API em objetos do dominio e vice versa.
Principios aplicados:
SOLID - SRP: centraliza mapeamento e normalizacao, evitando duplicacao em controllers.
DDD - Boundary translation: traduz linguagem externa (JSON) para linguagem de dominio.
Patterns: Mapper.
Observacao: Normalizacao de CPF ocorre aqui ao criar o Value Object, garantindo invariantes.
*/

public class AlunoApiMapper {

    public Aluno toDomain(AlunoRequestDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO nulo.");
        }

        Aluno aluno = new Aluno();
        aluno.setCpf(new Cpf(dto.getCpf()));
        aluno.setNome(dto.getNome());
        aluno.setIdade(dto.getIdade());
        aluno.setMatricula(dto.getMatricula());
        return aluno;
    }

    public AlunoResponseDto toResponse(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno nulo.");
        }

        AlunoResponseDto dto = new AlunoResponseDto();
        dto.setId(aluno.getId());
        dto.setCpf(aluno.getCpf() != null ? aluno.getCpf().getNumero() : null);
        dto.setNome(aluno.getNome());
        dto.setIdade(aluno.getIdade());
        dto.setMatricula(aluno.getMatricula());
        return dto;
    }
}
