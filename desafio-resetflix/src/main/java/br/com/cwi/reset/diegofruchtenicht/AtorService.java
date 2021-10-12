package br.com.cwi.reset.diegofruchtenicht;

import java.time.LocalDate;
import java.util.List;

public class AtorService {

    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor (AtorRequest atorRequest){

        Ator ator = new Ator ("", LocalDate.of(1970,1,1),1970,0000,StatusCarreira.APOSENTADO);

        ator.setId(1234);
        ator.setNome(atorRequest.getNome());
        ator.setDataNascimento(atorRequest.getDataNascimento());
        ator.setAnoInicioAtividade(atorRequest.getAnoInicioAtividade());
        ator.setStatusCarreira(atorRequest.getStatusCarreira());

        fakeDatabase.persisteAtor(ator);


    }

    public List listarAtoresEmAtividade (String filtroNome){

    }

    public Ator consultarAtor (int id){}

    public List consultarAtores () {}





}
