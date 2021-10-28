package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AtorVinculadoPersonagemException extends Exception{
    public AtorVinculadoPersonagemException() {
        super("Este ator está vinculado a um ou mais personagens, para remover o ator é necessário remover os seus personagens de atuação.");
    }
}
