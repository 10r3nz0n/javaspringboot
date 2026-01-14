package com.lorenzon.aluno.model;

import java.io.Serializable;

//IA code

import lombok.Value;

@Value
public class Cpf implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return numero;
    }
}
