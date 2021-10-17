package br.com.cwi.reset.diegofruchtenicht.controller;

import br.com.cwi.reset.diegofruchtenicht.FakeDatabase;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Estudio;
import br.com.cwi.reset.diegofruchtenicht.request.EstudioRequest;
import br.com.cwi.reset.diegofruchtenicht.service.EstudioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    private EstudioService estudioService;

    public EstudioController() {
        this.estudioService = new EstudioService(FakeDatabase.getInstance());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio (@RequestBody EstudioRequest estudioRequest) throws CamposObrigatoriosException, DataCriacaoEstudioException, NomeJaCadastradoException {
        estudioService.criarEstudio(estudioRequest);
    }

    @GetMapping
    public List<Estudio> consultarEstudios (@RequestParam String filtroNome) throws FiltroNaoEncontradoException, NaoCadastradoException {
        List <Estudio> estudiosCadastrados = new ArrayList<>();
        estudiosCadastrados = estudioService.consultarEstudios(filtroNome);

        return estudiosCadastrados;
    }

    @GetMapping ("/{id}")
    public Estudio consultarEstudio (@PathVariable Integer id) throws IDNaoEncontradoException, CamposObrigatoriosException {
        Estudio estudioConsultado = estudioService.consultarEstudio(id);

        return estudioConsultado;
    }


}
