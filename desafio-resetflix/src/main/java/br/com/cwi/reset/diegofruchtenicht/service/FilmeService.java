package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.FakeDatabase;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.*;
import br.com.cwi.reset.diegofruchtenicht.request.FilmeRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FilmeService {

    List<Filme> filmes = new ArrayList<>();

    private FakeDatabase fakeDatabase;

    private DiretorService diretorService;

    private EstudioService estudioService;

    private PersonagemService personagemService;

    public FilmeService (FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.diretorService = new DiretorService(FakeDatabase.getInstance());
        this.estudioService = new EstudioService(FakeDatabase.getInstance());
        this.personagemService = new PersonagemService(FakeDatabase.getInstance());
    }

    public void criarFilme (FilmeRequest filmeRequest) throws  IDNaoEncontradoException, GenerosIguaisException, AtorPersonagemRepetidosException {
        filmes = fakeDatabase.recuperaFilmes();

        // exception cadastro de dois generos iguais
        List <Genero> listaGeneroOrdenada = filmeRequest.getGeneros();

        Collections.sort(listaGeneroOrdenada);

        if (filmeRequest.getGeneros().size() > 1){
            for (int i=1; i < (listaGeneroOrdenada.size()); i++){
                if(listaGeneroOrdenada.get(i-1) == listaGeneroOrdenada.get(i) ){
                    throw new GenerosIguaisException();
                }
            }
        }

        // Carrega diretor pelo Id
        Diretor diretor = diretorService.consultarDiretor(filmeRequest.getIdDiretor());

        //Carrega estudio pelo Id
        Estudio estudio = estudioService.consultarEstudio(filmeRequest.getIdEstudio());

        //Gera Lista de Personagens
        List <PersonagemAtor> listaPersonagensAtorGerados = personagemService.criarPersonagem(filmeRequest.getPersonagens());

        //Obtem ultimo ID
        int ultimoId;

        if (filmes.size() > 0){
            ultimoId = filmes.get(filmes.size()-1).getId();
            ultimoId ++;
        }else{
            ultimoId = 0;
        }

        // Salva no Banco de Dados
        fakeDatabase.persisteFilme(new Filme(ultimoId,filmeRequest.getNome(),filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(),diretor,estudio,listaPersonagensAtorGerados,filmeRequest.getResumo()));

    }

    public List <Filme> consutarFilmes (String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws NaoCadastradoException, FilmeNaoEncontradoException {
        filmes = fakeDatabase.recuperaFilmes();

        List<Filme> filmesFiltrados = new ArrayList<>(filmes);
        List<Filme> filmesFiltradosNome = new ArrayList<>();
        List<Filme> filmesFiltradosDiretor = new ArrayList<>();
        List<Filme> filmesFiltradosPersonagem = new ArrayList<>();
        List<Filme> filmesFiltradosAtor = new ArrayList<>();


        if (filmes.size() > 0){
            if(nomeFilme.isEmpty() && nomeDiretor.isEmpty() && nomePersonagem.isEmpty() && nomeAtor.isEmpty()){
                return filmes;
            }else {
                if(!nomeFilme.isEmpty()){
                    for(Filme filme : filmes){
                        if(filme.getNome().contains(nomeFilme)){
                           filmesFiltradosNome.add(filme);
                        }
                    }
                    filmesFiltrados.retainAll(filmesFiltradosNome);
                }
                if(!nomeDiretor.isEmpty()){
                    for(Filme filme : filmes){
                        if(filme.getDiretor().getNome().contains(nomeDiretor)){
                            filmesFiltradosDiretor.add(filme);
                        }
                    }
                    filmesFiltrados.retainAll(filmesFiltradosDiretor);
                }
                if(!nomePersonagem.isEmpty()){
                    for(Filme filme : filmes){
                        for (PersonagemAtor personagem : filme.getPersonagens()){
                            if(personagem.getNomePersonagem().contains(nomePersonagem)){
                                filmesFiltradosPersonagem.add(filme);
                            }
                        }

                    }
                    filmesFiltrados.retainAll(filmesFiltradosPersonagem);
                }
                if(!nomeAtor.isEmpty()){
                    for(Filme filme : filmes){
                        for (PersonagemAtor personagem : filme.getPersonagens()){
                            if(personagem.getAtor().getNome().contains(nomeAtor)){
                                filmesFiltradosAtor.add(filme);
                            }
                        }

                    }
                    filmesFiltrados.retainAll(filmesFiltradosAtor);
                }

                if(filmesFiltrados==null || filmesFiltrados.isEmpty()){
                    throw new FilmeNaoEncontradoException(nomeFilme,nomeDiretor,nomePersonagem,nomeAtor);
                }

                return filmesFiltrados;

            }

        }else {
            throw new NaoCadastradoException("filme", "filmes");
        }


    }
}
