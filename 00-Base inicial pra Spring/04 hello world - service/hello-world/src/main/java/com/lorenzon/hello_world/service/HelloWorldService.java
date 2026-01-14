package com.lorenzon.hello_world.service;

import org.springframework.stereotype.Service;

@Service // indica que a classe é serviço
public class HelloWorldService {

    public String hellowWorld(String nome) {

        return "Hellow World, " + nome + "!";

    }

}
