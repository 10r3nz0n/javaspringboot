package com.lorenzon.aluno.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*

Introdução. POJO (pôu djôu)
            é um termo do ecossistema Java que descreve uma classe simples, “pura”, sem dependência obrigatória de frameworks ou heranças especiais. Quando alguém diz “isso aqui é um POJO”, normalmente quer dizer: é só uma classe Java comum, usada para carregar estado e comportamento básico.

Conceito (Bloom: lembrar e compreender)
POJO significa Plain Old Java Object. Na prática, é uma classe que:
não precisa estender classes específicas de frameworks
não precisa implementar interfaces especiais para “funcionar”
não depende de anotações para existir como objeto, embora possa ter anotações sem deixar de ser POJO
pode ter atributos, construtores, getters, setters, equals, hashCode e métodos de regra

O termo surgiu como contraste a estilos mais “pesados” do Java corporativo antigo, em que classes tinham que seguir contratos rígidos (por exemplo, componentes EJB antigos exigiam interfaces e heranças específicas). POJO reforça a ideia de simplicidade e baixo acoplamento.

Análise (Bloom: analisar e avaliar)
O que você pode dizer quando aparece “POJO”
Depende do contexto, mas geralmente você pode afirmar:

POJO é uma classe Java comum, sem acoplamento forte a infraestrutura.
POJO pode representar domínio, DTO, entidade, ou qualquer modelo simples.
POJO não é sinônimo de DTO, nem sinônimo de “classe anêmica”, apesar de frequentemente ser usado assim.
POJO não significa “sem anotação”. Uma classe com Lombok ou JPA annotations ainda pode ser vista como POJO, desde que a lógica dela não dependa de herança ou runtime específico para existir. O ponto é o acoplamento conceitual e estrutural.

*/

// Classe anêmica, "sem encapsulamento" explicitado pois as anotações abaixo tratam disso automaticamente

/*
 * 
 * O Lombok gera getters e setters em tempo de compilação. Se você anota a
 * classe com @Setter, ele tenta gerar setNome, setIdade e setMatricula.
 * Porém, se você já escreveu um método setIdade manualmente, o Lombok não gera
 * outro com a mesma assinatura, e o seu método é o que vale.
 * 
 * Abordagem: manter @Setter na classe e escrever setIdade manual
 * Vantagens: menor esforço, mantém padrão, você customiza só o necessário.
 * Risco: você pode acabar colocando regra de domínio (validação) em entidade
 * anêmica, o que conflita com um design mais orientado a domínio.
 * Ainda assim, para didática e CRUD simples, é aceitável.
 * 
 * 
 * 
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Aluno {

    @Setter
    private Cpf cpf; // Value Object

    @Setter
    private String nome;

    private int idade; // não terá @Setter

    @Setter
    private String matricula;

    // Quando existe um método explícito no código, ele prevalece; o Lombok não
    // “força” você a aceitar o método padrão.

    public void setIdade(int idade) {

        // fail-first, happy path

        if (idade < 0) {
            throw new IllegalArgumentException("Idade invalida.");
        }

        this.idade = idade;

    }

    // pensando em DDD, não teria um Setter de idade e sim mais encapsulamento real,
    // menos anemia
    public void alterarIdade(int novaIdade) {
        if (novaIdade < 0) {
            throw new IllegalArgumentException("Idade invalida");
        }
        this.idade = novaIdade;
    }

}
