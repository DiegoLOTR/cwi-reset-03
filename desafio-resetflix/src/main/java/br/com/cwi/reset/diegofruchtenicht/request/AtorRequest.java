package br.com.cwi.reset.diegofruchtenicht.request;

import br.com.cwi.reset.diegofruchtenicht.model.DadosPessoas;
import br.com.cwi.reset.diegofruchtenicht.model.StatusCarreira;

import java.time.LocalDate;

public class AtorRequest extends DadosPessoas {

    private StatusCarreira statusCarreira;

    public AtorRequest(String nome, LocalDate dataNascimento, Integer anoInicioAtividade, StatusCarreira statusCarreira) {
        super(nome, dataNascimento, anoInicioAtividade);
        this.statusCarreira = statusCarreira;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }
}
