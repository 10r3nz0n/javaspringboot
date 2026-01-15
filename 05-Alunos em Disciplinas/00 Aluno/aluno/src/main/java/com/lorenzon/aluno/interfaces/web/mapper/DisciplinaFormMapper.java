package com.lorenzon.aluno.interfaces.web.mapper;

import com.lorenzon.aluno.domain.model.Disciplina;
import com.lorenzon.aluno.interfaces.web.dto.DisciplinaForm;

public class DisciplinaFormMapper {

    public DisciplinaForm toForm(Disciplina disciplina) {
        DisciplinaForm form = new DisciplinaForm();
        form.setCodigo(disciplina.getCodigo());
        form.setDescricao(disciplina.getDescricao());
        return form;
    }
}
