package br.com.cwi.reset.diegofruchtenicht.controller;

import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Filme;
import br.com.cwi.reset.diegofruchtenicht.request.FilmeRequest;
import br.com.cwi.reset.diegofruchtenicht.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    public FilmeController() {
        this.filmeService = new FilmeService();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme (@RequestBody @Valid FilmeRequest filmeRequest) throws IDNaoEncontradoException, GenerosIguaisException, AtorPersonagemRepetidosException {
        filmeService.criarFilme(filmeRequest);
    }

    @GetMapping
    public List<Filme> consultarFilmes (@RequestParam String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws NaoCadastradoException, FilmeNaoEncontradoException {
        List <Filme> filmesCadastrados = filmeService.consutarFilmes(nomeFilme, nomeDiretor, nomePersonagem, nomeAtor);

        return filmesCadastrados;
    }
}
