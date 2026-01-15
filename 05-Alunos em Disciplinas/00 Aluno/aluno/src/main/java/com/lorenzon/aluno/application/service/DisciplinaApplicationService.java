package com.lorenzon.aluno.application.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lorenzon.aluno.domain.model.Aluno;
import com.lorenzon.aluno.domain.model.Disciplina;
import com.lorenzon.aluno.domain.repository.AlunoRepositoryInterface;
import com.lorenzon.aluno.domain.repository.DisciplinaRepositoryInterface;

@Service
public class DisciplinaApplicationService {

    private final DisciplinaRepositoryInterface disciplinaRepository;
    private final AlunoRepositoryInterface alunoRepository;

    public DisciplinaApplicationService(
            DisciplinaRepositoryInterface disciplinaRepository,
            AlunoRepositoryInterface alunoRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Disciplina criar(String codigo, String descricao) {
        validarCodigoUnico(codigo, null);
        Disciplina disciplina = new Disciplina(codigo, descricao);
        return disciplinaRepository.save(disciplina);
    }

    @Transactional(readOnly = true)
    public List<Disciplina> listar() {
        return new ArrayList<>(disciplinaRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Optional<Disciplina> buscarPorId(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return disciplinaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Disciplina> buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            return Optional.empty();
        }
        return disciplinaRepository.findByCodigo(codigo.trim());
    }

    @Transactional
    public Disciplina atualizar(Long id, String codigo, String descricao) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina nao encontrada."));

        validarCodigoUnico(codigo, disciplina.getId());

        disciplina.alterarCodigo(codigo);
        disciplina.alterarDescricao(descricao);

        return disciplinaRepository.save(disciplina);
    }

    @Transactional
    public void excluir(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id obrigatorio.");
        }
        if (!disciplinaRepository.existsById(id)) {
            throw new IllegalArgumentException("Disciplina nao encontrada.");
        }
        disciplinaRepository.deleteById(id);
    }

    @Transactional
    public Disciplina atualizarMatriculas(Long disciplinaId, List<Long> alunoIds) {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina nao encontrada."));

        Set<Aluno> novosAlunos = new HashSet<>();
        if (alunoIds != null) {
            for (Long alunoId : alunoIds) {
                if (alunoId == null) {
                    continue;
                }
                Aluno aluno = alunoRepository.findById(alunoId)
                        .orElseThrow(() -> new IllegalArgumentException("Aluno nao encontrado: " + alunoId));
                novosAlunos.add(aluno);
            }
        }

        disciplina.substituirMatriculas(novosAlunos);
        return disciplinaRepository.save(disciplina);
    }

    private void validarCodigoUnico(String codigo, Long idAtual) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Codigo da disciplina obrigatorio.");
        }
        String codigoNormalizado = codigo.trim();

        Optional<Disciplina> existente = disciplinaRepository.findByCodigo(codigoNormalizado);
        if (existente.isEmpty()) {
            return;
        }
        if (idAtual == null) {
            throw new IllegalArgumentException("Codigo da disciplina ja existe.");
        }
        if (!existente.get().getId().equals(idAtual)) {
            throw new IllegalArgumentException("Codigo da disciplina ja existe.");
        }
    }
}
