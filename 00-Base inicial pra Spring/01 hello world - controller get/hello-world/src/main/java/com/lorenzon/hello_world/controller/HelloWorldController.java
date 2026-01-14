package com.lorenzon.hello_world.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// indica que a classe é um controle Rest - Response Body
// agrega + @Controller e @ResponseBody
// STATELESS -> token / STATEFULL -> mantém o estado do cliente: São estilos
// arquiteturais de como você mantém (ou não mantém) estado de sessão do cliente
// entre requisições.

/*
 * Você pode fazer requisições GET por URL tanto com @Controller quanto
 * com @RestController.
 * O que muda não é o fato de “ser GET”, e sim como o Spring interpreta o valor
 * retornado pelo método.
 * 
 * Em termos práticos, o que aconteceu com você é o comportamento padrão
 * do @Controller:
 * quando o método retorna uma String, o Spring interpreta essa String como nome
 * de uma view (template) para renderizar,
 * e não como corpo da resposta HTTP. Então, se você retorna "Hello World"
 * com @Controller, o Spring tenta procurar um template chamado Hello World (ou
 * equivalente) em resources/templates. Como isso não existe, a requisição “para
 * de funcionar” como você esperava.
 * 
 * Já o @RestController equivale a @Controller junto com @ResponseBody. Então, o
 * retorno do método vira diretamente o corpo HTTP
 * (texto, JSON etc). Por isso, com @RestController seu Hello World funciona de
 * imediato.
 */

@RestController 
@RequestMapping("/hello-world")
// Este controller escuta todas as requisições que chegam nesta url

public class HelloWorldController {
    // Métodos de resposta: post, get, delete, put, patch, options, head :

    //@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE) // escuta o hello-word e responde o método get na raiz /hello-world
    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }
    
    // responde /hello-world + /get
    //@GetMapping(value = "/get", produces = MediaType.TEXT_PLAIN_VALUE)
    @GetMapping("/get")
    public String helloWorldGet() {
        return "Hello World - get!";
    }

}

/*
 * 
 * GET
 * Ler recurso
 * Exemplos:
 * GET /api/alunos
 * GET /api/alunos/{id}
 * 
 * POST
 * Criar recurso
 * Exemplos:
 * POST /api/alunos
 * POST /api/alunos/batch
 * 
 * PUT
 * Substituir o recurso inteiro (atualizacao completa)
 * Exemplo:
 * PUT /api/alunos/{id}
 * 
 * PATCH
 * Atualizacao parcial
 * Exemplo:
 * PATCH /api/alunos/{id}
 * 
 * DELETE
 * Remover recurso
 * Exemplo:
 * DELETE /api/alunos/{id}
 * 
 * Isso é convenção REST baseada na semântica HTTP. O Spring trabalha
 * naturalmente assim com @GetMapping, @PostMapping etc.
 *
 * 
 * 
 * 
 * Stateless
 * Cada requisição contém tudo que o servidor precisa para processar.
 * O servidor não mantém sessão do cliente.
 * Autenticação normalmente via token (JWT ou outro) no header Authorization.
 * É o estilo mais comum para API REST moderna.
 * 
 * Stateful
 * O servidor mantém uma sessão associada ao cliente (cookie de sessão, por
 * exemplo JSESSIONID).
 * A cada requisição o cliente envia o cookie e o servidor recupera estado da
 * sessão.
 * Muito comum em aplicações web tradicionais com login e páginas HTML
 * renderizadas.
 * 
 * Onde isso aparece na prática
 * Quando você usa Thymeleaf com @Controller e navega com browser, por padrão é
 * natural ter stateful quando você adicionar autenticação. Browser trabalha com
 * cookie facilmente.
 * 
 * Quando você expõe /api com @RestController, o padrão é stateless. Insomnia,
 * Postman, mobile apps e front-end moderno consomem melhor stateless.
 * 
 * Importante
 * Uma mesma aplicação Spring Boot pode ter ambos ao mesmo tempo:
 * web MVC stateful para páginas
 * API REST stateless para clientes externos
 * 
 * Isso é default no Spring Boot
 * Sem Spring Security
 * Sua aplicação não está “nem stateless nem stateful” de forma relevante,
 * porque você não tem autenticação nem sessão sendo usada.
 * As requisições funcionam do mesmo jeito.
 * 
 * Com Spring Security
 * O default historicamente tende a ser mais stateful para web (sessão) e pode
 * ser ajustado para stateless em API.
 * 
 */
