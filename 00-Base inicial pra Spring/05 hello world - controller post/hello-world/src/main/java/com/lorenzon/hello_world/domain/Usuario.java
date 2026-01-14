package com.lorenzon.hello_world.domain;

//Usando lombok pra automações de código
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Dando isntruções para automação, evitando a verbosisade e tendo apenas o resultado automaticamente
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // para manter o contstrutor vazio e funcionar com jackson

public class Usuario {

    private String nome;

}
