package com.lorenzon.aluno.interfaces.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lorenzon.aluno.application.service.AlunoApplicationService;
import com.lorenzon.aluno.domain.model.Aluno;
import com.lorenzon.aluno.interfaces.api.dto.AlunoRequestDto;
import com.lorenzon.aluno.interfaces.api.dto.AlunoResponseDto;
import com.lorenzon.aluno.interfaces.api.mapper.AlunoApiMapper;

/*
NOTA DE ARQUITETURA
Camada: Interfaces (API REST)
Responsabilidade: Expor endpoints JSON, acionar casos de uso e retornar status HTTP adequados.
Principios aplicados:
SOLID - SRP: controla apenas fluxo HTTP, sem regra de negocio.
Arquitetura em camadas: depende de Application, nao depende de Infrastructure diretamente.
DDD - Boundary: controlador e uma borda de entrada, nao contem dominio.
Boas praticas REST: usa metodos HTTP e ResponseEntity para expressar resultado.
Observacao: Conversao DTO <-> dominio ocorre via Mapper, evitando acoplamento com entidades internas.
*/

@RestController
@RequestMapping("/api/alunos")
public class AlunoRestController {

    private final AlunoApplicationService alunoService;
    private final AlunoApiMapper mapper;

    public AlunoRestController(AlunoApplicationService alunoService) {
        this.alunoService = alunoService;
        this.mapper = new AlunoApiMapper();
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDto> criar(@Valid @RequestBody AlunoRequestDto request) {
        Aluno aluno = mapper.toDomain(request);
        Aluno salvo = alunoService.cadastrar(aluno);

        AlunoResponseDto response = mapper.toResponse(salvo);
        URI location = URI.create("/api/alunos/" + salvo.getId());
        return ResponseEntity.created(location).body(response);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<AlunoResponseDto>> criarVarios(@Valid @RequestBody List<AlunoRequestDto> requestList) {
        List<AlunoResponseDto> resposta = new ArrayList<>();

        for (AlunoRequestDto request : requestList) {
            Aluno aluno = mapper.toDomain(request);
            Aluno salvo = alunoService.cadastrar(aluno);
            resposta.add(mapper.toResponse(salvo));
        }

        return ResponseEntity.ok(resposta);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDto>> listar() {
        List<Aluno> alunos = alunoService.listar();
        List<AlunoResponseDto> resposta = new ArrayList<>();

        for (Aluno aluno : alunos) {
            resposta.add(mapper.toResponse(aluno));
        }

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDto> buscarPorId(@PathVariable("id") Long id) {
        return alunoService.buscarPorId(id)
                .map(aluno -> ResponseEntity.ok(mapper.toResponse(aluno)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<AlunoResponseDto> buscarPorCpf(@PathVariable("cpf") String cpf) {
        return alunoService.buscarPorCpf(cpf)
                .map(aluno -> ResponseEntity.ok(mapper.toResponse(aluno)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        alunoService.excluirPorId(id);
        return ResponseEntity.noContent().build();
    }
}
