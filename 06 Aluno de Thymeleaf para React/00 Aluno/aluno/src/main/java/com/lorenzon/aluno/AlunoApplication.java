package com.lorenzon.aluno;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
  Nota de arquitetura:
  SRP: esta classe apenas inicializa a aplicacao
*/

@SpringBootApplication
public class AlunoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlunoApplication.class, args);
    }
}
