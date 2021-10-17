package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.FakeDatabase;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Ator;
import br.com.cwi.reset.diegofruchtenicht.model.Diretor;
import br.com.cwi.reset.diegofruchtenicht.model.Estudio;
import br.com.cwi.reset.diegofruchtenicht.request.EstudioRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstudioService {

    List<Estudio> estudios = new ArrayList<>();
    private FakeDatabase fakeDatabase;
    public EstudioService (FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarEstudio (EstudioRequest estudioRequest) throws CamposObrigatoriosException, NomeJaCadastradoException, DataCriacaoEstudioException {

        estudios = fakeDatabase.recuperaEstudios();
        LocalDate hoje = LocalDate.now();

        //exception campos nulos
        if(estudioRequest.getNome().equals(null) || estudioRequest.getNome().isEmpty()){
            throw new CamposObrigatoriosException("Nome");
        }else if(estudioRequest.getDescricao().equals(null) || estudioRequest.getDescricao().isEmpty()){
            throw new CamposObrigatoriosException("Descrição");
        }else if(estudioRequest.getDataCriacao() == null){
            throw new CamposObrigatoriosException("Data de Criação");
        }else if(estudioRequest.getStatusAtividade() == null){
            throw new CamposObrigatoriosException("Status Atividade");
        }

        // exception nome ja cadastrado
        for (Estudio estudio : estudios){
            if(estudio.getNome().equals(estudioRequest.getNome())) {
                throw new NomeJaCadastradoException("estúdio",estudioRequest.getNome());
            }
        }

        // exception Data de Criação
        if (estudioRequest.getDataCriacao().isAfter(hoje) ){
            throw new DataCriacaoEstudioException();
        }

        //Obtem ultimo ID
        int lastID;

        if (estudios.size() > 0){
            lastID = estudios.get(estudios.size()-1).getId();
            lastID ++;
        }else{
            lastID = 0;
        }

        // Salva no Banco de Dados
        fakeDatabase.persisteEstudio(new Estudio(lastID,estudioRequest.getNome(),estudioRequest.getDescricao(),estudioRequest.getDataCriacao(),estudioRequest.getStatusAtividade()));


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

    public Estudio consultarEstudio (Integer id) throws CamposObrigatoriosException, IDNaoEncontradoException {

        estudios = fakeDatabase.recuperaEstudios();
        boolean idEncontrado = false;

        if (id == null){
            throw new CamposObrigatoriosException("id");
        }else{
            for (Estudio estudio: estudios){
                if(estudio.getId()==id){
                    idEncontrado = true;
                    return estudio;
                }
            }
        }
        if(!idEncontrado){
            throw new IDNaoEncontradoException("estúdio",id);
        }else{

        }

        return null;

    }

}
