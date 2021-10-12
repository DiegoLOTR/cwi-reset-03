package br.com.cwi.reset.diegofruchtenicht;

import java.time.LocalDate;

public class Ator extends DadosPessoas{

    private int id;
    private StatusCarreira statusCarreira;

    public Ator(String nome, LocalDate dataNascimento, int anoInicioAtividade, int id, StatusCarreira statusCarreira) {
        super(nome, dataNascimento, anoInicioAtividade);
        this.id = id;
        this.statusCarreira = statusCarreira;
    }

    public int getId() {
        return id;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatusCarreira(StatusCarreira statusCarreira) {
        this.statusCarreira = statusCarreira;
    }

}
