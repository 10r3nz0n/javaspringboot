package com.lorenzon.aluno.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

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
