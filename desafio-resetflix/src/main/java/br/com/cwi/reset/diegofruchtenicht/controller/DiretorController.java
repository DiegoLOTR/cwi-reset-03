package br.com.cwi.reset.diegofruchtenicht.controller;

import br.com.cwi.reset.diegofruchtenicht.*;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Diretor;
import br.com.cwi.reset.diegofruchtenicht.request.DiretorRequest;
import br.com.cwi.reset.diegofruchtenicht.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;

    public DiretorController() {
        this.diretorService = new DiretorService();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarDiretor (@RequestBody @Valid DiretorRequest diretorRequest) throws  NomeSobrenomeException, NomeJaCadastradoException, AnoInicioAtividadeException {
        diretorService.cadastrarDiretor(diretorRequest);

    }

    @GetMapping
    public List<Diretor> listarDiretores (@RequestParam String filtroNome) throws FiltroNaoEncontradoException, NaoCadastradoException {
        List<Diretor> diretoresCadastrados = diretorService.listarDiretores(filtroNome);

        return diretoresCadastrados;
    }

    @GetMapping ("/{id}")
    public Diretor consultarDiretor (@PathVariable @NotNull Integer id) throws  IDNaoEncontradoException {
        Diretor diretorConsultado = diretorService.consultarDiretor(id);

        return diretorConsultado;
    }

    @DeleteMapping("/{id}")
    public void removerDiretores (@PathVariable @NotNull Integer id) throws IDNaoEncontradoException, DiretorVinculadoFilmeException {
        diretorService.removerDiretores(id);
    }


    @PutMapping("/{id}")
    public void atualizarDiretor (@PathVariable Integer id, @RequestBody @Valid DiretorRequest diretorRequest ) throws IDNaoEncontradoException, NomeJaCadastradoException, AnoInicioAtividadeException, NomeSobrenomeException {
        diretorService.atualizarDiretor(id,diretorRequest);
    }

}
