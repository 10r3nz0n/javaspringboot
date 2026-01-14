package com.lorenzon.hello_world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzon.hello_world.service.HelloWorldService;

@RestController
@RequestMapping("/hello-world")

public class HelloWorldController {

    @Autowired // indica que este atributo é injetado, não precisando declarar a injeção no
               // contrutor, aqui começam as mágicas dos frameworks, por isso precisa seguir a risca
               // as nomenclaturas e conhecer os conceitos

    private HelloWorldService helloWorldService;

    /*
     * public HelloWorldController(HelloWorldService helloWorldService) { // injeção
     * this.helloWorldService = helloWorldService;
     * }
     */ //Retirado por causa de @Autowired acima, então nem precisou-se fazer qualquer instanciação no código, tudo automático

    @GetMapping
    public String hellowWorld() {
        // return "Hello World!";
        return helloWorldService.hellowWorld("Lorenzon");
    }

    @GetMapping("/get")
    public String hellowWorldGet() {
        return "Hello World - get!";
    }

}
