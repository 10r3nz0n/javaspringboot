package com.lorenzon.hello_world.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzon.hello_world.service.HelloWorldService;

@RestController
@RequestMapping("/hello-world")

public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

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
