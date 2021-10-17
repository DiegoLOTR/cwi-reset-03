package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NomeJaCadastradoException extends Exception{
    public NomeJaCadastradoException(String nomeDominio, String nome) {
        super("Já existe um "+ nomeDominio+ " cadastrado para o nome " + nome + ".");
    }
}
