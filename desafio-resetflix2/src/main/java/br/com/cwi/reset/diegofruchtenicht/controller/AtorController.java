package br.com.cwi.reset.diegofruchtenicht.controller;

import br.com.cwi.reset.diegofruchtenicht.*;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Ator;
import br.com.cwi.reset.diegofruchtenicht.request.AtorRequest;
import br.com.cwi.reset.diegofruchtenicht.response.AtorEmAtividade;
import br.com.cwi.reset.diegofruchtenicht.service.AtorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    private AtorService atorService;

    public AtorController() {
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor (@RequestBody AtorRequest atorRequest) throws CamposObrigatoriosException, NomeSobrenomeException, NomeJaCadastradoException, AnoInicioAtividadeException, NaoNascidosException {
        atorService.criarAtor(atorRequest);

    }

    @GetMapping
    public List <Ator> consultarAtores () throws CamposObrigatoriosException, NaoCadastradoException {
        List<Ator> atoresCadastrados = atorService.consultarAtores();

        return atoresCadastrados;
    }

    @GetMapping ("/{id}")
    public Ator consultarAtor (@PathVariable Integer id) throws CamposObrigatoriosException, IDNaoEncontradoException {
        Ator atorConsultado = atorService.consultarAtor(id);

        return atorConsultado;
    }

    @GetMapping ("/em_atividade")
    public List <AtorEmAtividade> listarAtoresEmAtividade (@RequestParam String filtroNome) throws CamposObrigatoriosException, FiltroNaoEncontradoException, NaoCadastradoException, NenhumAtorAtividade {
        List<AtorEmAtividade> atoresEmAtividade = atorService.listarAtoresEmAtividade(filtroNome);

        return atoresEmAtividade;

    }


}
