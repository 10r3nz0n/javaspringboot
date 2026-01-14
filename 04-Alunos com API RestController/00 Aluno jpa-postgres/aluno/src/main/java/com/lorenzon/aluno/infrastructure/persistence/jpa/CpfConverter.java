package com.lorenzon.aluno.infrastructure.persistence.jpa;

import com.lorenzon.aluno.domain.model.Cpf;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/*
  JPA: adaptador de persistencia para o Value Object
  DDD: mantem Cpf como VO no dominio e armazena String na tabela
*/

@Converter(autoApply = true)
public class CpfConverter implements AttributeConverter<Cpf, String> {

    @Override
    public String convertToDatabaseColumn(Cpf attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getNumero();
    }

    @Override
    public Cpf convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return null;
        }
        return new Cpf(dbData);
    }
}
