package com.lorenzon.hello_world.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzon.hello_world.domain.Usuario;
import com.lorenzon.hello_world.repository.UsuarioRepository;
import com.lorenzon.hello_world.service.HelloWorldService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/hello-world")

public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping
    public String helloWorld() {
        // return "Hello World!";
        return helloWorldService.helloWorld("Lorenzon");
    }

    @GetMapping("/get")
    public String helloWorldGet() {
        return helloWorldService.helloWorld("Hello World - get!");
    }

    // Pegando parâmetros url com get, como se fosse, mas não é post
    // usar: //localhost:3000/hello-world/get-post?nome=XXXX
    @GetMapping("/get-post")
    public String helloWorldGet(@RequestParam("nome") String nome) {
        return helloWorldService.helloWorld(nome);
    }

    /*
     * curl.exe -i -X POST "http://localhost:3000/hello-world/post/Junior"
     * 
     * Imnsonia:
     * 
     * POST localhost:3000/hello-world/post/TESTE
     * 
     */

    @PostMapping("/post/{nome}") // raiz
    public String helloWorldPost(@PathVariable("nome") String nome) {

        return helloWorldService.helloWorld(nome); // get foi criado pelo loombok
    }

    // a url não faz chamada post, é sempre get

    /*
     * Terminal:
     * 
     * $body = @{ nome = "Junior" } | ConvertTo-Json; Invoke-RestMethod -Uri
     * "http://localhost:3000/hello-world/post" -Method Post -ContentType
     * "application/json" -Body $body
     * 
     * 
     * Imnsonia:
     * 
     * POST
     * 
     * http://localhost:3000/hello-world/post
     * 
     * Body, Selecione JSON e informe:
     * {
     * "nome": "Junior"
     * }
     */

    @PostMapping("/post") // raiz
    public String helloWorldPost(@RequestBody Usuario usuario) {// criando automaticamente a injeção
        return helloWorldService.helloWorld(usuario.getNome()); // get foi criado pelo loombok
    }

    /*
     * 
     * Imnsonia: POST
     * 
     * localhost:3000/hello-world/post/usuarios
     * 
     * [
     * { "nome": "Junior" },
     * { "nome": "Ana" },
     * { "nome": "Pedro" }
     * ]
     * }
     */

    // oriundo de um JSON com vários nomes: rota
    @PostMapping(value = "/post/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)

    public String helloWorldPostUsuarios(@RequestBody List<Usuario> usuarios) {

        String saida = "";

        if (usuarios != null) {
            for (int i = 0; i < usuarios.size(); i++) {
                // Criando o objeto Usuario
                Usuario u = usuarios.get(i);
                if (u != null && u.getNome() != null) {
                    saida = saida + helloWorldService.helloWorld(u.getNome()) + "\n";
                }
            }
        }

        return (saida);
    }
}
