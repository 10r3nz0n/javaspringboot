package com.lorenzon.aluno.domain.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "disciplina", uniqueConstraints = {
        @UniqueConstraint(name = "uk_disciplina_codigo", columnNames = { "codigo" })
})

@Getter
@Setter
@AllArgsConstructor
public class Disciplina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true, length = 30)
    private String codigo;

    @Column(name = "descricao", nullable = false, length = 200)
    private String descricao;

    @ManyToMany
    @JoinTable(name = "disciplina_aluno", joinColumns = @JoinColumn(name = "disciplina_id"), inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private Set<Aluno> alunos = new HashSet<>();

    public Disciplina() {
        // JPA
    }

    public Disciplina(String codigo, String descricao) {
        this.id = null;
        this.alunos = new HashSet<>();
        alterarCodigo(codigo);
        alterarDescricao(descricao);
    }

    public void alterarCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Codigo da disciplina obrigatorio.");
        }
        this.codigo = codigo.trim();
    }

    public void alterarDescricao(String descricao) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descricao da disciplina obrigatoria.");
        }
        this.descricao = descricao.trim();
    }

    public void matricular(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno obrigatorio para matricula.");
        }
        if (this.alunos == null) {
            this.alunos = new HashSet<>();
        }
        this.alunos.add(aluno);
    }

    public void desmatricular(Aluno aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno obrigatorio para desmatricula.");
        }
        if (this.alunos == null) {
            this.alunos = new HashSet<>();
        }
        this.alunos.remove(aluno);
    }

    public void substituirMatriculas(Set<Aluno> novosAlunos) {
        if (this.alunos == null) {
            this.alunos = new HashSet<>();
        }
        this.alunos.clear();

        if (novosAlunos != null) {
            this.alunos.addAll(novosAlunos);
        }
    }
}
