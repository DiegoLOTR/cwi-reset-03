package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CamposObrigatoriosException extends Exception{

    public CamposObrigatoriosException(String campo) {
        super("Campo obrigatório não informado. Favor informar o campo " + campo + " .");
    }
}
