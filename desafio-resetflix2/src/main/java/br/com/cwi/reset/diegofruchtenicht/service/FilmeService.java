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

    public void criarFilme (FilmeRequest filmeRequest) throws CamposObrigatoriosException, IDNaoEncontradoException, GenerosIguaisException, AtorPersonagemRepetidosException {
        filmes = fakeDatabase.recuperaFilmes();

        //exception campos nulos
        if(filmeRequest.getNome().equals(null) || filmeRequest.getNome().isEmpty()){
            throw new CamposObrigatoriosException("Nome");
        }else if(filmeRequest.getAnoLancamento()==null){
            throw new CamposObrigatoriosException("Ano de Lançamento");
        }else if(filmeRequest.getCapaFilme() == null || filmeRequest.getCapaFilme().isEmpty()){
            throw new CamposObrigatoriosException("Capa do Filme");
        }else if(filmeRequest.getIdDiretor() == null){
            throw new CamposObrigatoriosException("Id do Diretor");
        }else if(filmeRequest.getIdEstudio() == null){
            throw new CamposObrigatoriosException("Id do Estúdio");
        }else if (filmeRequest.getResumo() == null || filmeRequest.getResumo().isEmpty()){
            throw new CamposObrigatoriosException("Resumo");
        }else if(filmeRequest.getPersonagens() == null){
            throw new CamposObrigatoriosException("Lista de Personagens");
        }

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

    public List <Filme> consutarFilmes (String nomeFilme, String nomeDiretor, String nomePersonagem, String nomeAtor) throws NaoCadastradoException {
        filmes = fakeDatabase.recuperaFilmes();

        List<Filme> filmesFiltrados = new ArrayList<>();

        if (filmes.size() > 0){
            if(nomeFilme.isEmpty() && nomeDiretor.isEmpty() && nomePersonagem.isEmpty() && nomeAtor.isEmpty()){
                return filmes;
            }else {

            }

        }else {
            throw new NaoCadastradoException("filme", "filmes");
        }

        return filmes;
    }
}
