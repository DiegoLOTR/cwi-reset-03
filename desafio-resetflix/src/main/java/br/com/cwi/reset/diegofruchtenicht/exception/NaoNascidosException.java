package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NaoNascidosException extends Exception{
    public NaoNascidosException(String nomeDominio) {
        super("Não é possível cadastrar "+nomeDominio+" não nascidos.");
    }
}
