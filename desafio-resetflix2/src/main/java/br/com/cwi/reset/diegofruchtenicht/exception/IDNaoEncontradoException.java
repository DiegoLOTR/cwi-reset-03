package br.com.cwi.reset.diegofruchtenicht.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IDNaoEncontradoException extends Exception{

    public IDNaoEncontradoException(String nomeDominio, Integer id) {
        super("Nenhum " +nomeDominio+ " encontrado com o parâmetro id={"+ id +"}, favor verifique os parâmetros informados.");
    }
}
