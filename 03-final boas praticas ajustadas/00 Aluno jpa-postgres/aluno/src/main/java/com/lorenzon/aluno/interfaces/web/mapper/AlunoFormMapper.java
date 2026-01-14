package com.lorenzon.aluno.interfaces.web.mapper;

import com.lorenzon.aluno.domain.model.Aluno;
import com.lorenzon.aluno.interfaces.web.dto.AlunoForm;

/*
  Pattern: Data Mapper
  SOLID SRP: mapeamento apenas
*/

public class AlunoFormMapper {

    public AlunoForm toForm(Aluno aluno) {
        AlunoForm form = new AlunoForm();
        form.setId(aluno.getId());
        form.setCpf(aluno.getCpf() != null ? aluno.getCpf().getNumero() : "");
        form.setNome(aluno.getNome());
        form.setIdade(aluno.getIdade());
        form.setMatricula(aluno.getMatricula());
        return form;
    }
}
