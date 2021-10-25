package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilmeNaoEncontradoException extends Exception{
    public FilmeNaoEncontradoException(String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) {
        super("Filme não encontrato com os filtros nomeFilme={"+nomeFilme+"}, nomeDiretor={"+nomeDiretor+"}, nomePersonagem={"+nomePersonagem+"}, nomeAtor={"+nomeAtor+"}, favor informar outros filtros.");
    }
}
