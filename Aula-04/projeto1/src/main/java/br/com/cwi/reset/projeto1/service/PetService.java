package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    public Pet buscarPeloNome(String nomePet) throws PetNaoExistenteException {
        Pet pet = repository.findByNomeIgnoringCase(nomePet);

        if (pet == null) {
            throw new PetNaoExistenteException("Pet com o nome " + nomePet+ " não existe");
        }

        return pet;
    }

    public List<Pet> listarPets() {
        return repository.findAll();
    }

    public Pet cadastrarPet(Pet pet) throws PetJaExistenteException {
        Pet petJaExistente = repository.findByNomeIgnoringCase(pet.getNome());

        if (petJaExistente != null) {
            throw new PetJaExistenteException("Pet com o nome " + pet.getNome() + " já existe");
        }

        return repository.save(pet);
    }

    public void atualizarPet( Pet pet) throws PetNaoExistenteException {
        Pet petExistente = repository.findByNomeIgnoringCase(pet.getNome());

        if (petExistente == null) {
            throw new PetNaoExistenteException("Pet com o nome " + pet.getNome()+ " não existe");
        }

       repository.save(pet);
    }

    public void deletarPet(String nomePet) throws PetNaoExistenteException {
        Pet pet = repository.findByNomeIgnoringCase(nomePet);

        if (pet == null) {
            throw new PetNaoExistenteException("Pet com o nome " + nomePet+ " não existe");
        }

        repository.delete(pet);
    }

}
