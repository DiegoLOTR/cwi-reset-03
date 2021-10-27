package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Estudio;
import br.com.cwi.reset.diegofruchtenicht.repository.EstudioRepository;
import br.com.cwi.reset.diegofruchtenicht.request.EstudioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepository estudioRepository;

    List<Estudio> estudios = new ArrayList<>();

    public void criarEstudio (EstudioRequest estudioRequest) throws  NomeJaCadastradoException {
        estudios = estudioRepository.findAll();

        // exception nome ja cadastrado
        for (Estudio estudio : estudios){
            if(estudio.getNome().equals(estudioRequest.getNome())) {
                throw new NomeJaCadastradoException("estúdio",estudioRequest.getNome());
            }
        }

        // Salva no Banco de Dados
        estudioRepository.save(new Estudio(estudioRequest.getNome(),estudioRequest.getDescricao(),estudioRequest.getDataCriacao(),estudioRequest.getStatusAtividade()));

    }

    public List<Estudio> consultarEstudios (String filtroNome) throws NaoCadastradoException, FiltroNaoEncontradoException {
        estudios = estudioRepository.findAll();

        List <Estudio> estudiosFiltrados = new ArrayList<>();

        if(estudios.size()>0){
            if(filtroNome.equals("")){
                return estudios;
            }else{
                for(int i =0 ; i < estudios.size() ; i++){
                    if(estudios.get(i).getNome().contains(filtroNome)){
                        estudiosFiltrados.add(estudios.get(i));
                    }
                }
                if(estudiosFiltrados.size()<=0){
                    throw new FiltroNaoEncontradoException("Estúdio", filtroNome );
                }
            }
        }else{
            throw new NaoCadastradoException("estúdio", "estúdios");
        }

        return estudiosFiltrados;
    }

    public Estudio consultarEstudio (Integer id) throws IDNaoEncontradoException {

        return estudioRepository.findById(id).orElseThrow(() -> new IDNaoEncontradoException("estúdio",id));

    }

}

