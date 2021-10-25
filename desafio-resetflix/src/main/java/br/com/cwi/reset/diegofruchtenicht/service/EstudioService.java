package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.FakeDatabase;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Estudio;
import br.com.cwi.reset.diegofruchtenicht.request.EstudioRequest;
import java.util.ArrayList;
import java.util.List;

public class EstudioService {

    List<Estudio> estudios = new ArrayList<>();

    private FakeDatabase fakeDatabase;

    public EstudioService (FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarEstudio (EstudioRequest estudioRequest) throws  NomeJaCadastradoException {
        estudios = fakeDatabase.recuperaEstudios();

        // exception nome ja cadastrado
        for (Estudio estudio : estudios){
            if(estudio.getNome().equals(estudioRequest.getNome())) {
                throw new NomeJaCadastradoException("estúdio",estudioRequest.getNome());
            }
        }

        //Obtem ultimo ID
        int ultimoId;

        if (estudios.size() > 0){
            ultimoId = estudios.get(estudios.size()-1).getId();
            ultimoId ++;
        }else{
            ultimoId = 0;
        }

        // Salva no Banco de Dados
        fakeDatabase.persisteEstudio(new Estudio(ultimoId,estudioRequest.getNome(),estudioRequest.getDescricao(),estudioRequest.getDataCriacao(),estudioRequest.getStatusAtividade()));

    }

    public List<Estudio> consultarEstudios (String filtroNome) throws NaoCadastradoException, FiltroNaoEncontradoException {
        estudios = fakeDatabase.recuperaEstudios();

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
        estudios = fakeDatabase.recuperaEstudios();

        boolean idEncontrado = false;

            for (Estudio estudio: estudios){
                if(estudio.getId()==id){
                    idEncontrado = true;
                    return estudio;
                }
            }


        if(!idEncontrado){
            throw new IDNaoEncontradoException("estúdio",id);
        }

        return null;

    }

}

