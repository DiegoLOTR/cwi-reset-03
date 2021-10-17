package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NomeSobrenomeException extends Exception{
    public NomeSobrenomeException(String nomeDominio) {
        super("Deve ser informado no mínimo nome e sobrenome para o " + nomeDominio + " .");
    }
}
