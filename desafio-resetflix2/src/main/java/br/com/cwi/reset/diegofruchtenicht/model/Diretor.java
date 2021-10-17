package br.com.cwi.reset.diegofruchtenicht.model;

import java.time.LocalDate;

public class Diretor extends DadosPessoas {

    private Integer id;

    public Diretor(String nome, LocalDate dataNascimento, Integer anoInicioAtividade, Integer id) {
        super (nome, dataNascimento, anoInicioAtividade);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
