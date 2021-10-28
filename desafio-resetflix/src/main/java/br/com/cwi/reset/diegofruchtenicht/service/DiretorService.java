package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Diretor;
import br.com.cwi.reset.diegofruchtenicht.model.Filme;
import br.com.cwi.reset.diegofruchtenicht.repository.DiretorRepository;
import br.com.cwi.reset.diegofruchtenicht.repository.FilmeRepository;
import br.com.cwi.reset.diegofruchtenicht.request.DiretorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    List<Diretor> diretores = new ArrayList<>();

    public void cadastrarDiretor (DiretorRequest diretorRequest) throws  NomeSobrenomeException, NomeJaCadastradoException, AnoInicioAtividadeException {
        diretores = diretorRepository.findAll();

        LocalDate hoje = LocalDate.now();

        // exception nome e sobrenome
        if ((diretorRequest.getNome().split(" ").length < 2)){
            throw new NomeSobrenomeException("diretor");
        }

        // exception nome ja cadastrado
        Diretor diretorMesmoNome = diretorRepository.findByNomeIgnoringCase(diretorRequest.getNome());

        if (diretorMesmoNome != null){
            throw new NomeJaCadastradoException("diretor" , diretorRequest.getNome());
        }

        // exception Inicio da Atividade
        if (diretorRequest.getAnoInicioAtividade()< diretorRequest.getDataNascimento().getYear() || diretorRequest.getAnoInicioAtividade() > hoje.getYear() ){
            throw new AnoInicioAtividadeException("diretor");
        }


        // Salva no Banco de Dados
        diretorRepository.save(new Diretor (diretorRequest.getNome(),diretorRequest.getDataNascimento(),diretorRequest.getAnoInicioAtividade()));

    }

    public List listarDiretores (String filtroNome) throws  FiltroNaoEncontradoException, NaoCadastradoException {
        diretores = diretorRepository.findAll();

        List <Diretor> diretoresFiltrados = new ArrayList<>();

        if(diretores.size()>0){
            if(filtroNome.equals("")){
                return diretores;
            }else{
                for(int i =0 ; i < diretores.size() ; i++){
                    if(diretores.get(i).getNome().contains(filtroNome)){
                        diretoresFiltrados.add(diretores.get(i));
                    }
                }

                if(diretoresFiltrados.size()<=0){
                    throw new FiltroNaoEncontradoException("Diretor", filtroNome );
                }
            }

        }else{
            throw new NaoCadastradoException("diretor", "diretores");
        }

        return diretoresFiltrados;
    }

    public Diretor consultarDiretor (Integer id) throws  IDNaoEncontradoException {

        return diretorRepository.findById(id).orElseThrow(() -> new IDNaoEncontradoException("diretor",id));

    }

    public void removerDiretores (Integer id) throws IDNaoEncontradoException, DiretorVinculadoFilmeException {

        Diretor diretorEncontrado = diretorRepository.findById(id).orElseThrow(() -> new IDNaoEncontradoException("diretor",id));

        List<Filme> filmesEncontrados = filmeRepository.findByDiretor(diretorEncontrado);

        if (filmesEncontrados.size()>0){
            throw  new DiretorVinculadoFilmeException();
        }

        diretorRepository.deleteById(id);

    }

    public void atualizarDiretor (Integer id , DiretorRequest diretorRequest ) throws IDNaoEncontradoException, NomeJaCadastradoException, NomeSobrenomeException, AnoInicioAtividadeException {

        if(!diretorRepository.existsById(id)){
            throw new IDNaoEncontradoException("diretor",id);
        }

        Diretor diretorMesmoNome = diretorRepository.findByNomeIgnoringCase(diretorRequest.getNome());

        if (diretorMesmoNome != null) {
            if (diretorMesmoNome.getId() != id) {
                throw new NomeJaCadastradoException("diretor", diretorRequest.getNome());
            }
        }

        if (diretorRequest.getAnoInicioAtividade()< diretorRequest.getDataNascimento().getYear() ){
            throw new AnoInicioAtividadeException("diretor");
        }

        if ((diretorRequest.getNome().split(" ").length < 2)){
            throw new NomeSobrenomeException("diretor");
        }

        Diretor diretorUpdate = new Diretor(diretorRequest.getNome(),diretorRequest.getDataNascimento(),diretorRequest.getAnoInicioAtividade());

        diretorUpdate.setId(id);

        diretorRepository.save(diretorUpdate);

    }
}

