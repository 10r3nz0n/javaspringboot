package com.lorenzon.aluno.interfaces.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/*
 * NOTA DE ARQUITETURA
 * Camada: Interfaces (Contrato de entrada)
 * Responsabilidade: Definir formato do JSON de entrada e regras de validacao de
 * payload.
 * Principios aplicados:
 * SOLID - SRP: apenas dados e validacao, sem regras de negocio.
 * Arquitetura: estabiliza contrato da API sem expor modelo interno do dominio.
 * DDD - Anti corruption (parcial): protege dominio de formatos externos.
 * Observacao: Usar Jakarta Validation aqui evita falhas tardias no banco e
 * reduz erros 500.
 */

public class AlunoRequestDto {

    @NotBlank
    private String cpf;

    @NotBlank
    private String nome;

    @NotNull
    @Min(0)
    private Integer idade;

    @NotBlank
    private String matricula;

}
