package com.lorenzon.aluno.interfaces.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/*
 * NOTA DE ARQUITETURA
 * Camada: Interfaces (Contrato de saida)
 * Responsabilidade: Definir formato do JSON de resposta, controlando campos
 * expostos.
 * Principios aplicados:
 * SOLID - SRP: somente representacao de saida.
 * Arquitetura: evita retornar entidade JPA ou dominio diretamente, reduzindo
 * acoplamento.
 * Seguranca e evolucao: permite versionar API e ocultar campos internos.
 */

public class AlunoResponseDto {

    private Long id;
    private String cpf;
    private String nome;
    private Integer idade;
    private String matricula;

}
