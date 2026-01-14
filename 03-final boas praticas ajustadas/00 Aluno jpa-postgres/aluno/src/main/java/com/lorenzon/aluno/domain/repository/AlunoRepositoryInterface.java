package com.lorenzon.aluno.domain.repository;

import java.util.List;
import java.util.Optional;

import com.lorenzon.aluno.domain.model.Aluno;
import com.lorenzon.aluno.domain.model.Cpf;

/*
  DDD: porta de repositorio (abstracao de dominio)
  SOLID DIP: application depende desta abstracao, nao de Spring Data
*/

public interface AlunoRepositoryInterface {

  Aluno save(Aluno aluno);

  List<Aluno> findAll();

  Optional<Aluno> findById(Long id);

  Optional<Aluno> findByCpf(Cpf cpf);

  Optional<Aluno> findByMatricula(String matricula);

  void deleteById(Long id);

  boolean existsById(Long id);
}

/*
 * Optional é um tipo do Java usado para representar explicitamente “pode
 * existir valor, pode não existir”, evitando retornar null e reduzindo
 * NullPointerException quando bem usado.
 * 
 * Taxonomia de Bloom
 * 
 * Conceito
 * 
 * Optional<T>
 * Um container que pode estar vazio ou conter um valor do tipo T.
 * 
 * Por que existe
 * Ele força você, no ponto de uso, a tratar o caso de ausência de valor, ao
 * invés de assumir que sempre existe e depois estourar NPE.
 * 
 * Como ele aparece no seu projeto
 * Spring Data JPA retorna Optional em métodos como findById, findByCpf,
 * findByMatricula, porque uma consulta pode não encontrar nenhum registro.
 * 
 * Exemplo conceitual
 * Buscar aluno por id pode retornar:
 * Optional com aluno, se existe.
 * Optional vazio, se não existe.
 * 
 * Análise
 * 
 * O que Optional resolve e o que ele não resolve
 * 
 * Resolve
 * Evitar null como retorno de busca.
 * Expressar intenção de “talvez exista”.
 * Incentivar fluxo com tratamento explícito, por exemplo mostrar mensagem
 * “Aluno não encontrado”.
 * 
 * Não resolve
 * Optional não é mecanismo de validação.
 * Optional não substitui exceções em regras de negócio quando ausência é erro.
 * Optional não deve ser usado como campo de entidade JPA ou atributo de classe,
 * porque complica serialização e persistência. Use em retornos de método.
 * 
 * Formas comuns de consumo
 * 
 * isPresent e get
 * Funciona, mas não é a forma mais elegante porque pode virar if encadeado.
 * 
 * orElse e orElseGet
 * Define um default quando não tem valor.
 * 
 * orElseThrow
 * Quando ausência é erro, você lança uma exceção com mensagem clara.
 * 
 * ifPresent
 * Executa uma ação somente se existe valor.
 * 
 * map
 * Transforma o valor se existir, mantendo Optional.
 * 
 * flatMap
 * Quando a transformação também retorna Optional, evitando Optional de
 * Optional.
 * 
 * Prática
 * 
 * 3.1 Exemplo aplicado ao seu CRUD
 * 
 * Repository
 * 
 * Optional<Aluno> alunoOpt = alunoRepositorio.findById(id);
 * 
 * 
 * Caso 1, ausência é erro no fluxo de edição
 * 
 * Aluno aluno = alunoRepositorio.findById(id)
 * .orElseThrow(() -> new IllegalArgumentException("Aluno nao encontrado."));
 * 
 * 
 * Caso 2, ausência não é erro, apenas lista vazia na busca
 * 
 * List<Aluno> resultado = new ArrayList<>();
 * alunoRepositorio.findById(id).ifPresent(resultado::add);
 * 
 * 
 * Caso 3, transformar para DTO se existir
 * 
 * Optional<AlunoForm> formOpt = alunoRepositorio.findById(id)
 * .map(aluno -> {
 * AlunoForm form = new AlunoForm();
 * form.setId(aluno.getId());
 * form.setCpf(aluno.getCpf().getNumero());
 * form.setNome(aluno.getNome());
 * form.setIdade(aluno.getIdade());
 * form.setMatricula(aluno.getMatricula());
 * return form;
 * });
 * 
 * 
 * 3.2 Ponto de atenção importante
 * 
 * Nunca faça isto sem checar:
 * 
 * Aluno a = alunoRepositorio.findById(id).get();
 * 
 * 
 * Porque se não existir, get lança NoSuchElementException e você perde controle
 * da mensagem e do tratamento.
 * 
 * Tópicos relacionados ao contexto
 * Diferença entre Optional vazio e exceção em regra de negócio
 * Tratamento de “não encontrado” em web MVC versus em REST (404)
 * Uso de Optional no Spring Data JPA
 * 
 */
