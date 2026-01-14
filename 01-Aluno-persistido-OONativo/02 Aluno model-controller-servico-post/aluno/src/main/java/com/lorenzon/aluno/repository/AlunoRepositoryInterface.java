package com.lorenzon.aluno.repository;

import java.util.List;

import com.lorenzon.aluno.model.Aluno;

/*
    Esta interface define o contrato de persistencia para Aluno.

    O objetivo e desacoplar a camada de servico dos detalhes de infraestrutura.
    O servico depende desta abstracao e nao de uma implementacao concreta.

    Assim, podemos trocar a forma de persistencia sem reescrever a regra de negocio,
    por exemplo: repositorio em memoria, banco relacional, ou banco NoSQL orientado a documentos,
    desde que a nova implementacao respeite este contrato.

    Isso aplica o DIP (Dependency Inversion Principle): modulos de alto nivel (servicos)
    e modulos de baixo nivel (persistencia) dependem de abstracoes, e os detalhes dependem do contrato.

*/

public interface AlunoRepositoryInterface {

    void restaurarTodos();

    void salvar(Aluno aluno);

    List<Aluno> listar();

}
