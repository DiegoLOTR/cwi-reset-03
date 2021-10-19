package br.com.cwi.reset.diegofruchtenicht.controller;


import br.com.cwi.reset.diegofruchtenicht.FakeDatabase;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.request.EstudioRequest;
import br.com.cwi.reset.diegofruchtenicht.request.FilmeRequest;
import br.com.cwi.reset.diegofruchtenicht.service.EstudioService;
import br.com.cwi.reset.diegofruchtenicht.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private FilmeService filmeService;
    public FilmeController() {
        this.filmeService = new FilmeService(FakeDatabase.getInstance());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme (@RequestBody FilmeRequest filmeRequest) throws IDNaoEncontradoException, CamposObrigatoriosException, GenerosIguaisException, AtorPersonagemRepetidosException {
        filmeService.criarFilme(filmeRequest);
    }
}
