package br.com.cwi.reset.diegofruchtenicht;

import java.time.LocalDate;

public class Diretor extends DadosPessoas{

    private int id;

    public Diretor(String nome, LocalDate dataNascimento, int anoInicioAtividade, int id) {
        super (nome, dataNascimento, anoInicioAtividade);
        this.id = id;
    }
}
