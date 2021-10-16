package br.com.cwi.reset.primeiroprojetospring.controller;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/filme")

public class FilmeController {


    private static List<Filme> listaFilmes = new ArrayList();

    private Filme buscarFilmePorNome(String nome) {
        for (Filme filme : listaFilmes) {
            if (filme.getNome().equals(nome)) {
                return filme;
            }
        }
        return null;
    }

    //localhost:8080/filme => criar Json no formato do objeto filme, em RAW no corpo (body) da mensagem HTTP
    @PostMapping
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody Filme filme) {

        if (buscarFilmePorNome(filme.getNome()) != null) {
            return ResponseEntity.badRequest().build();
        }

        listaFilmes.add(filme);
        return ResponseEntity.ok(filme);
    }

    //localhost:8080/filme
    @GetMapping
    public List<Filme> buscarFilmes() {
        return listaFilmes;
    }

    //localhost:8080/nome
    @GetMapping("/{nome}")
    public Filme buscarFilmeNome(@PathVariable String nome) {
        Filme filme = buscarFilmePorNome(nome);
        return filme;
    }

    //localhost:8080/filme/nome
    @DeleteMapping ("/{nome}")
    public void deletarFilme (@PathVariable String nome){
        Filme filme = buscarFilmePorNome(nome);
        if (filme != null){
            listaFilmes.remove(filme);
        }
    }

    //localhost:8080/filme => criar Json no formato do objeto filme, em RAW no corpo (body) da mensagem HTTP
    @PutMapping
    public Filme editarFilme(@RequestBody Filme filme) {

        Filme filmeCadastrado = buscarFilmePorNome(filme.getNome());

        if (filmeCadastrado != null) {
             listaFilmes.remove(filmeCadastrado);
             listaFilmes.add(filme);
             return filme;

        }

        return  null;

    }

}






