package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class GenerosIguaisException extends Exception{
    public GenerosIguaisException() {
        super("Não é permitido informar o mesmo gênero mais de uma vez para o mesmo filme.");
    }
}
