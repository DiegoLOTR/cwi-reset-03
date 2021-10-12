package br.com.cwi.reset.diegofruchtenicht;

import java.time.LocalDate;

public class DiretorRequest extends DadosPessoas{

    public DiretorRequest(String nome, LocalDate dataNascimento, Integer anoInicioAtividade) {
        super(nome, dataNascimento, anoInicioAtividade);
    }
}
