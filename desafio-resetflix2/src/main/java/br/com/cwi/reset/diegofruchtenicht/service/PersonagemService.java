package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.FakeDatabase;
import br.com.cwi.reset.diegofruchtenicht.exception.AtorPersonagemRepetidosException;
import br.com.cwi.reset.diegofruchtenicht.exception.CamposObrigatoriosException;
import br.com.cwi.reset.diegofruchtenicht.exception.IDNaoEncontradoException;
import br.com.cwi.reset.diegofruchtenicht.model.Ator;
import br.com.cwi.reset.diegofruchtenicht.model.PersonagemAtor;
import br.com.cwi.reset.diegofruchtenicht.request.PersonagemRequest;
import java.util.ArrayList;
import java.util.List;

public class PersonagemService {

    List<PersonagemAtor> personagensExistentes = new ArrayList<>();

    List<PersonagemAtor> personagensNovos = new ArrayList<>();

    private FakeDatabase fakeDatabase;

    private AtorService atorService;

    public PersonagemService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.atorService = new AtorService((FakeDatabase.getInstance()));
    }

    public List <PersonagemAtor> criarPersonagem (List <PersonagemRequest> personagemRequest) throws IDNaoEncontradoException, CamposObrigatoriosException, AtorPersonagemRepetidosException {
        personagensExistentes = fakeDatabase.recuperaPersonagens();

        personagensNovos.clear();

        // exceptions campos nulos
        for(PersonagemRequest personagemRequisitado : personagemRequest){
            if (personagemRequisitado.getIdAtor() == null){
                throw new CamposObrigatoriosException("Id do Ator");
            }else if(personagemRequisitado.getNomePersonagem() == null || personagemRequisitado.getNomePersonagem().isEmpty()){
                throw new CamposObrigatoriosException("Nome do Personagem");
            }else if(personagemRequisitado.getDescricaoPersonagem() == null || personagemRequisitado.getDescricaoPersonagem().isEmpty()){
                throw new CamposObrigatoriosException("Descrição do Personagem");
            }
        }

        // Verifica se nao tem dois personagens iguais linkados ao mesmo ator
        if(personagemRequest.size() > 1){
            for(int i=0; i < personagemRequest.size(); i++){
                for (int j=i+1; j < personagemRequest.size(); j++){
                    if(personagemRequest.get(i).getNomePersonagem().equals(personagemRequest.get(j).getNomePersonagem())){
                        if (personagemRequest.get(i).getIdAtor() == personagemRequest.get(j).getIdAtor()){
                            throw new AtorPersonagemRepetidosException();
                        }
                    }
                }
            }
        }

        //Obtem ultimo ID
        int ultimoId;

        if (personagensExistentes.size() > 0){
            ultimoId = personagensExistentes.get(personagensExistentes.size()-1).getId();
            ultimoId ++;
        }else{
            ultimoId = 0;
        }

        //Criar o Ator, PersonagemAtor e Salva no Banco de Dados
        for (PersonagemRequest personagemValidado : personagemRequest){
            Ator ator = atorService.consultarAtor(personagemValidado.getIdAtor());
            PersonagemAtor personagemAtorCriado = new PersonagemAtor(ultimoId,ator,personagemValidado.getNomePersonagem(),personagemValidado.getDescricaoPersonagem(),personagemValidado.getTipoAtuacao());
            fakeDatabase.persistePersonagem(personagemAtorCriado);
            personagensNovos.add(personagemAtorCriado);
            ultimoId++;
        }

        return personagensNovos;
    }
}
