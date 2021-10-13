package br.com.cwi.reset.diegofruchtenicht;

import java.time.LocalDate;

public class Ator extends DadosPessoas{

    private Integer id;
    private StatusCarreira statusCarreira;

    public Ator(String nome, LocalDate dataNascimento, Integer anoInicioAtividade, Integer id, StatusCarreira statusCarreira) {
        super(nome, dataNascimento, anoInicioAtividade);
        this.id = id;
        this.statusCarreira = statusCarreira;
    }

    public Integer getId() {
        return id;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatusCarreira(StatusCarreira statusCarreira) {
        this.statusCarreira = statusCarreira;
    }

}
