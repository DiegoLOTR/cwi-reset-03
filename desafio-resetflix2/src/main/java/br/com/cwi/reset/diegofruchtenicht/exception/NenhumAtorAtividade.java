package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NenhumAtorAtividade extends Exception{
    public NenhumAtorAtividade() {
        super("Nenhum ator em Atividade");
    }
}
