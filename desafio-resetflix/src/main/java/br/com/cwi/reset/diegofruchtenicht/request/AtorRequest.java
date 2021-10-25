package br.com.cwi.reset.diegofruchtenicht.request;

import br.com.cwi.reset.diegofruchtenicht.model.DadosPessoas;
import br.com.cwi.reset.diegofruchtenicht.model.StatusCarreira;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AtorRequest extends DadosPessoas {

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Status Carreira")
    private StatusCarreira statusCarreira;

    public AtorRequest(String nome, LocalDate dataNascimento, Integer anoInicioAtividade, StatusCarreira statusCarreira) {
        super(nome, dataNascimento, anoInicioAtividade);
        this.statusCarreira = statusCarreira;
    }

    public StatusCarreira getStatusCarreira() {
        return statusCarreira;
    }
}
