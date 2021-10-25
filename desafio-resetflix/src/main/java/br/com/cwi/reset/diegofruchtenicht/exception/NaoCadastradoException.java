package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NaoCadastradoException extends Exception{
    public NaoCadastradoException(String nomeDominioSingular, String nomeDominioPlural) {
        super("Nenhum "+nomeDominioSingular + " cadastrado, favor cadastar " + nomeDominioPlural + " .");
    }
}
