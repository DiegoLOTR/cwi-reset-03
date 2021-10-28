package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.exception.AtorPersonagemRepetidosException;
import br.com.cwi.reset.diegofruchtenicht.exception.IDNaoEncontradoException;
import br.com.cwi.reset.diegofruchtenicht.model.Ator;
import br.com.cwi.reset.diegofruchtenicht.model.PersonagemAtor;
import br.com.cwi.reset.diegofruchtenicht.repository.PersonagemRepository;
import br.com.cwi.reset.diegofruchtenicht.request.PersonagemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;

    List<PersonagemAtor> personagensExistentes = new ArrayList<>();

    @Autowired
    private AtorService atorService;

    public List <PersonagemAtor> criarPersonagem (List <PersonagemRequest> personagemRequest) throws IDNaoEncontradoException, AtorPersonagemRepetidosException {

        List<PersonagemAtor> personagensNovos = new ArrayList<>();

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

        for (PersonagemRequest personagemValidado : personagemRequest){
            Ator ator = atorService.consultarAtor(personagemValidado.getIdAtor());
            PersonagemAtor personagemAtorCriado = new PersonagemAtor(ator,personagemValidado.getNomePersonagem(),personagemValidado.getDescricaoPersonagem(),personagemValidado.getTipoAtuacao());
            personagemRepository.save(personagemAtorCriado);
            personagensNovos.add(personagemAtorCriado);
        }

        return personagensNovos;
    }

    public void removerPersonagens (List<PersonagemAtor> personagensAtor){

        for (PersonagemAtor personagemParaDeletar : personagensAtor){
            personagemRepository.delete(personagemParaDeletar);
        }
    }

}
