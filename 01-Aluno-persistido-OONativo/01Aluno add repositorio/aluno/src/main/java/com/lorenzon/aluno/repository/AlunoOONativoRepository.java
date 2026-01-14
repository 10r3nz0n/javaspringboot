package com.lorenzon.aluno.repository;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.lorenzon.aluno.model.Aluno;

/* 
    O repositório OO nativo é uma implementação concreta do contrato AlunoRepositoryInterface. 
    Ele armazena os objetos em memória, sem banco, sem JPA, apenas em lista. 
    O método salvar adiciona na lista. O método listar retorna a própria lista ou uma cópia. 
    O método restoreAll normalmente é usado para reinicializar o storage a um estado conhecido, 
    por exemplo limpar e recarregar dados iniciais. 
*/

//@Primary
@Repository("alunoPersistidoemNativoOO")

public class AlunoOONativoRepository implements AlunoRepositoryInterface {

    private List<Aluno> alunos;
    private final File arquivo;

    public AlunoOONativoRepository() {
        this.alunos = new ArrayList<Aluno>();
        this.arquivo = new File("alunos.dat");
        // Aluno aluno = new Aluno(new Cpf("71688684972"), "Junior", 32, "11155");
        // //comentado pra primeira vez ter alunos cadastrados
        // salvar(aluno);
        restaurarTodos(); // naprimeira vez inverter, comentar esta linha e descomentar as de cima, depois
                          // librar esta e se quiser add descomentar as de baixo.
        // Aluno aluno = new Aluno(new Cpf("71688684972"), "Junior", 32, "11155");
        // //Comentado se quiser add mais alunos depois do primeiro
        // salvar(aluno);
    }

    @Override
    public void restaurarTodos() {

        // Earlies return usados com void pra não praticar elses e ter rapido retorno

        if (!arquivo.exists()) {
            alunos = new ArrayList<Aluno>();
            return;
        }

        try (FileInputStream fis = new FileInputStream(arquivo);

                ObjectInputStream ois = new ObjectInputStream(fis)) {

            Object obj = ois.readObject();

            if (obj instanceof List) {
                alunos = (List<Aluno>) obj;
                return;
            }

            alunos = new ArrayList<Aluno>();

        } catch (Exception e) {
            alunos = new ArrayList<Aluno>();
        }
    }

    public void salvarTodos(List<Aluno> alunos) {

        try (FileOutputStream fos = new FileOutputStream(arquivo);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(new ArrayList<Aluno>(alunos));

        } catch (Exception e) {
            throw new IllegalStateException("Falha ao persistir alunos em arquivo.", e);
        }
    }

    @Override
    public void salvar(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno nao pode ser nulo.");
        }
        alunos.add(aluno);
        salvarTodos(alunos);
    }

    @Override
    public List<Aluno> listar() {
        return new ArrayList<Aluno>(alunos); // retorna uma cópia da lista e não a própria lista - encapsulamento mais
                                             // forte.
    }
}