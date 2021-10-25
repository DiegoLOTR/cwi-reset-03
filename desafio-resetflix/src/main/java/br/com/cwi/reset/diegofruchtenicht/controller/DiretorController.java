package br.com.cwi.reset.diegofruchtenicht.controller;

import br.com.cwi.reset.diegofruchtenicht.*;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Diretor;
import br.com.cwi.reset.diegofruchtenicht.request.DiretorRequest;
import br.com.cwi.reset.diegofruchtenicht.service.DiretorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    private DiretorService diretorService;

    public DiretorController() {
        this.diretorService = new DiretorService(FakeDatabase.getInstance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor (@RequestBody DiretorRequest diretorRequest) throws CamposObrigatoriosException, NomeSobrenomeException, NomeJaCadastradoException, AnoInicioAtividadeException, NaoNascidosException {
        diretorService.cadastrarDiretor(diretorRequest);

    }

    @GetMapping
    public List<Diretor> listarDiretores (@RequestParam String filtroNome) throws CamposObrigatoriosException, FiltroNaoEncontradoException, NaoCadastradoException {
        List<Diretor> diretoresCadastrados = diretorService.listarDiretores(filtroNome);

        return diretoresCadastrados;
    }

    @GetMapping ("/{id}")
    public Diretor consultarDiretor (@PathVariable Integer id) throws CamposObrigatoriosException, IDNaoEncontradoException {
        Diretor diretorConsultado = diretorService.consultarDiretor(id);

        return diretorConsultado;
    }


}
