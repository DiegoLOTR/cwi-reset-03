package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.*;
import br.com.cwi.reset.diegofruchtenicht.repository.FilmeRepository;
import br.com.cwi.reset.diegofruchtenicht.request.FilmeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    List<Filme> filmes = new ArrayList<>();

    @Autowired
    private DiretorService diretorService;
    @Autowired
    private EstudioService estudioService;
    @Autowired
    private PersonagemService personagemService;

    public void criarFilme (FilmeRequest filmeRequest) throws  IDNaoEncontradoException, GenerosIguaisException, AtorPersonagemRepetidosException {

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

        // Salva no Banco de Dados
        filmeRepository.save(new Filme(filmeRequest.getNome(),filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(),estudio,diretor,listaPersonagensAtorGerados,filmeRequest.getResumo()));

    }

    public List <Filme> consutarFilmes (String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws NaoCadastradoException, FilmeNaoEncontradoException {
        filmes = filmeRepository.findAll();

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

    public void removerFilme (Integer id) throws IDNaoEncontradoException {

        Filme filmeEncontrado = filmeRepository.findById(id).orElseThrow(() -> new IDNaoEncontradoException("filme",id));

        personagemService.removerPersonagens(filmeEncontrado.getPersonagens());

        filmeRepository.deleteById(id);
    }
}

