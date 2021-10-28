package br.com.cwi.reset.diegofruchtenicht.controller;

import br.com.cwi.reset.diegofruchtenicht.*;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Ator;
import br.com.cwi.reset.diegofruchtenicht.request.AtorRequest;
import br.com.cwi.reset.diegofruchtenicht.response.AtorEmAtividade;
import br.com.cwi.reset.diegofruchtenicht.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;

    public AtorController() {
        this.atorService = new AtorService();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor (@RequestBody @Valid AtorRequest atorRequest) throws NomeSobrenomeException, NomeJaCadastradoException, AnoInicioAtividadeException {
        atorService.criarAtor(atorRequest);

    }

    @GetMapping
    public List <Ator> consultarAtores () throws  NaoCadastradoException {
        List<Ator> atoresCadastrados = atorService.consultarAtores();

        return atoresCadastrados;
    }

    @GetMapping ("/{id}")
    public Ator consultarAtor (@PathVariable @Valid Integer id) throws IDNaoEncontradoException {
        Ator atorConsultado = atorService.consultarAtor(id);

        return atorConsultado;
    }

    @GetMapping ("/em_atividade")
    public List <AtorEmAtividade> listarAtoresEmAtividade (@RequestParam String filtroNome) throws FiltroNaoEncontradoException, NaoCadastradoException, NenhumAtorAtividade {
        List<AtorEmAtividade> atoresEmAtividade = atorService.listarAtoresEmAtividade(filtroNome);

        return atoresEmAtividade;

    }

    @DeleteMapping("/{id}")
    public void removerAtor (@PathVariable @Valid Integer id) throws IDNaoEncontradoException, AtorVinculadoPersonagemException {
        atorService.removerAtor(id);
    }

    @PutMapping("/{id}")
    public void atualizarAtor (@PathVariable Integer id, @RequestBody @Valid AtorRequest atorRequest ) throws IDNaoEncontradoException, NomeJaCadastradoException, AnoInicioAtividadeException, NomeSobrenomeException {
       atorService.atualizarAtor(id,atorRequest);
    }


}
