package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AnoInicioAtividadeException extends Exception{
    public AnoInicioAtividadeException(String nomeDominio) {
        super("Ano de início de atividade inválido para o " +nomeDominio+ " cadastrado.");
    }
}
