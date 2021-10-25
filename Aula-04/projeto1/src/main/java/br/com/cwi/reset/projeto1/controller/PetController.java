package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.exception.PetNaoExistenteException;
import br.com.cwi.reset.projeto1.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    public List<Pet> getPet() {
        return petService.listarPets();
    }

    @GetMapping("/{nomePet}")
    public Pet getByNome(@PathVariable String nomePet) throws PetNaoExistenteException {
        Pet pet = petService.buscarPeloNome(nomePet);
        return pet;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet cadastrarPet(@RequestBody Pet pet) throws PetJaExistenteException {
        petService.cadastrarPet(pet);
        return pet;
    }

    @PutMapping
    public void atualizarPet(@RequestBody Pet pet) throws PetNaoExistenteException {
        petService.atualizarPet(pet);
    }

    @DeleteMapping("/{nomePet}")
    public void deletarPet(@PathVariable String nomePet) throws PetNaoExistenteException {
       petService.deletarPet(nomePet);
    }

}
