package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.exception.AtorJaExistenteException;
import br.com.cwi.reset.projeto1.exception.AtorNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.AtorRepository;
import br.com.cwi.reset.projeto1.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtorService {

    @Autowired
    private AtorRepository repository;

    private void criarAtor (Ator ator) throws AtorJaExistenteException {
        Ator atorCriado = repository.findByNameIgnoringCase(ator.getNome());

        if (atorCriado != null){
            throw new AtorJaExistenteException("Ja existe um Ator com o nome de "+ ator.getNome() + " .");
        }

        repository.save(ator);
    }

    private Ator consultarAtorNome (String nome) throws AtorNaoExistenteException {
        Ator atorConsultado = repository.findByNameIgnoringCase(nome);

        if (atorConsultado == null){
            throw new AtorNaoExistenteException("Não existe atores com nome de " + nome +" ." );
        }

        return atorConsultado;

    }

//    private Ator consultarAtorNumeroOscar (Integer numeroOscars) {
//
//    }

    private void deletarAtor (Integer id){
        Ator atorConsultado = repository.findById(id);


    }

}
