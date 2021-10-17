package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataCriacaoEstudioException extends Exception{
    public DataCriacaoEstudioException() {
        super("Não é possível cadastrar estúdios do futuro.");
    }
}
