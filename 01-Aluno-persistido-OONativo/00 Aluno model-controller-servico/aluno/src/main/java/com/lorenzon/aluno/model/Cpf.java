package com.lorenzon.aluno.model;

//IA code

import lombok.Value;

/*  @Value é uma anotação do Lombok para criar uma classe imutável, tipicamente usada como Value Object no sentido de DDD.
    Não tem relação com “value objects” do Spring @Value (injeção de propriedades). Aqui estamos falando de Lombok.

    @Value transforma a classe em um tipo imutável, com estas características, de forma automática:
    - gera getters para todos os campos
    - gera um construtor com todos os campos como parâmetros
    - torna a classe final
    - torna todos os campos private final
    - gera equals e hashCode baseados nos campos
    - gera toString
 */

@Value
public class Cpf {

    String numero; // armazenar apenas digitos

    public Cpf(String numero) {
        String normalizado = normalizar(numero);
        if (!isValido(normalizado)) {
            throw new IllegalArgumentException("CPF invalido");
        }
        this.numero = normalizado;
    }

    private static String normalizar(String cpf) {
        if (cpf == null) {
            return "";
        }
        return cpf.replaceAll("\\D", "");
    }

    private static boolean isValido(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }

        // rejeita sequencias iguais: 00000000000, 11111111111, etc
        char primeiro = cpf.charAt(0);
        boolean tudoIgual = true;
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != primeiro) {
                tudoIgual = false;
                break;
            }
        }
        if (tudoIgual) {
            return false;
        }

        int dv1 = calcularDigito(cpf, 9, 10);
        int dv2 = calcularDigito(cpf, 10, 11);

        int informado1 = cpf.charAt(9) - '0';
        int informado2 = cpf.charAt(10) - '0';

        return dv1 == informado1 && dv2 == informado2;
    }

    private static int calcularDigito(String cpf, int quantidade, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;

        for (int i = 0; i < quantidade; i++) {
            int digito = cpf.charAt(i) - '0';
            soma += digito * peso;
            peso--;
        }

        int resto = soma % 11;
        if (resto < 2) {
            return 0;
        }
        return 11 - resto;
    }
}
