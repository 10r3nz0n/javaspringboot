package com.lorenzon.hello_world.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Para conseguir fazer algo como injeções de classes externas e não apenas as do Spring
//Classes de configuração para indicar usar a classe como base

@Configuration
public class HelloWorldConfiguration {

    /*
     * Exemplo
     * 
     * @Bean // usada dentro de @Configuration, para criar instancias de classes que
     * não são
     * // gerenciadas pelo Spring;Combinado com @Autowired
     * public ClassRetorno classeRetorno() {
     * return new ClassRetorno();
     * }
     * 
     */

}
