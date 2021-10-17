package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FiltroNaoEncontradoException extends Exception{
    public FiltroNaoEncontradoException(String nomeDominio, String filtroNome) {
        super(nomeDominio + " não encontrato com o filtro "+ filtroNome +", favor informar outro filtro.");
    }
}
