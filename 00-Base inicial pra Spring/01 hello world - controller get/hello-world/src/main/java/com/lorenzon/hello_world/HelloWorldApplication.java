package com.lorenzon.hello_world;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/*
Anotações abstraem configurações que o sprintg carrega, usando Reflection.
O que foi abstraído: @Configuration @EnabledAutoConfiguration e @ComponentScan
 */

@SpringBootApplication // indicação do entry point do sistema
@Profile("dev")
public class HelloWorldApplication { // sufixo padrão

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args); // executando a aplicação por sprint
	}

}
