package com.lorenzon.aluno.domain.model;

import java.io.Serializable;
import java.util.Objects;

/*
  DDD: Value Object
  Object calisthenics: evita obsessao por tipos primitivos para CPF
  SOLID: regra de dominio fica aqui, nao no controller
*/

public final class Cpf implements Serializable {

    private final String numero;

    public Cpf(String numero) {
        String normalizado = normalizar(numero);
        validar(normalizado);
        this.numero = normalizado;
    }

    public String getNumero() {
        return numero;
    }

    private String normalizar(String valor) {
        if (valor == null) {
            return "";
        }
        return valor.replaceAll("\\D", "");
    }

    private void validar(String valor) {

        /*
         * if (valor == null || valor.isBlank()) {
         * throw new IllegalArgumentException("CPF obrigatorio.");
         * }
         * 
         * if (valor.length() != 11) {
         * throw new IllegalArgumentException("CPF deve ter 11 digitos.");
         * }
         * 
         * if (todosDigitosIguais(valor)) {
         * throw new IllegalArgumentException("CPF invalido.");
         * }
         * 
         * if (!passaDigitosVerificadores(valor)) {
         * throw new IllegalArgumentException("CPF invalido.");
         * }
         * 
         */

    }

    private boolean todosDigitosIguais(String valor) {
        char primeiro = valor.charAt(0);
        for (int i = 1; i < valor.length(); i++) {
            if (valor.charAt(i) != primeiro) {
                return false;
            }
        }
        return true;
    }

    private boolean passaDigitosVerificadores(String valor) {
        int dv1 = calcularDigito(valor, 9, 10);
        int dv2 = calcularDigito(valor, 10, 11);
        return dv1 == (valor.charAt(9) - '0') && dv2 == (valor.charAt(10) - '0');
    }

    private int calcularDigito(String valor, int tamanho, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;
        for (int i = 0; i < tamanho; i++) {
            soma += (valor.charAt(i) - '0') * peso;
            peso--;
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : (11 - resto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cpf)) {
            return false;
        }
        Cpf other = (Cpf) o;
        return Objects.equals(numero, other.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return numero;
    }
}
