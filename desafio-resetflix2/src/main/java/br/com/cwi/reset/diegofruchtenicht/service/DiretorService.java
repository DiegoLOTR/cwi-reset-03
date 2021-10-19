package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.FakeDatabase;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Diretor;
import br.com.cwi.reset.diegofruchtenicht.request.DiretorRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiretorService {

    List<Diretor> diretores = new ArrayList<>();

    private FakeDatabase fakeDatabase;
    public DiretorService (FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor (DiretorRequest diretorRequest) throws CamposObrigatoriosException, NomeSobrenomeException, NomeJaCadastradoException, AnoInicioAtividadeException, NaoNascidosException {
        diretores = fakeDatabase.recuperaDiretores();

        LocalDate hoje = LocalDate.now();

        //exception campos nulos
        if(diretorRequest.getNome().equals(null) || diretorRequest.getNome().isEmpty()){
            throw new CamposObrigatoriosException("Nome");
        }else if(diretorRequest.getDataNascimento() == null){
            throw new CamposObrigatoriosException("Data Nascimento");
        }else if(diretorRequest.getAnoInicioAtividade() == null){
            throw new CamposObrigatoriosException("Ano Início Atividade");
        }

        // exception nome e sobrenome
        if ((diretorRequest.getNome().split(" ").length < 2)){
            throw new NomeSobrenomeException("diretor");
        }

        // exception nome ja cadastrado
        for (Diretor diretor : diretores){
            if(diretor.getNome().equals(diretorRequest.getNome())) {
                throw new NomeJaCadastradoException("diretor" , diretorRequest.getNome());
            }
        }

        // exception Data de Nascimento
        if (diretorRequest.getDataNascimento().isAfter(hoje) ){
            throw new NaoNascidosException("diretores");
        }

        // exception Inicio da Atividade
        if (diretorRequest.getAnoInicioAtividade()< diretorRequest.getDataNascimento().getYear() || diretorRequest.getAnoInicioAtividade() > hoje.getYear() ){
            throw new AnoInicioAtividadeException("diretor");
        }

        //Obtem ultimo ID
        int ultimoId;

        if (diretores.size() > 0){
            ultimoId = diretores.get(diretores.size()-1).getId();
            ultimoId ++;
        }else{
            ultimoId = 0;
       }

        // Salva no Banco de Dados
        fakeDatabase.persisteDiretor(new Diretor (diretorRequest.getNome(),diretorRequest.getDataNascimento(),diretorRequest.getAnoInicioAtividade(),ultimoId));

    }

    public List listarDiretores (String filtroNome) throws CamposObrigatoriosException, FiltroNaoEncontradoException, NaoCadastradoException {
        diretores = fakeDatabase.recuperaDiretores();

        List <Diretor> diretoresFiltrados = new ArrayList<>();

        if(diretores.size()>0){
            if(filtroNome.equals("")){
                return diretores;
            }else{
                for(int i =0 ; i < diretores.size() ; i++){
                    if(diretores.get(i).getNome().contains(filtroNome)){
                        diretoresFiltrados.add(diretores.get(i));
                    }
                }

                if(diretoresFiltrados.size()<=0){
                    throw new FiltroNaoEncontradoException("Diretor", filtroNome );
                }
            }

        }else{
            throw new NaoCadastradoException("diretor", "diretores");
        }

        return diretoresFiltrados;
    }

    public Diretor consultarDiretor (Integer id) throws CamposObrigatoriosException, IDNaoEncontradoException {
        diretores = fakeDatabase.recuperaDiretores();

        boolean idEncontrado = false;

        if (id == null){
            throw new CamposObrigatoriosException("id");
        }else{
            for (Diretor diretor: diretores){
                if(diretor.getId()==id){
                    idEncontrado = true;
                    return new Diretor (diretor.getNome(),diretor.getDataNascimento(),diretor.getAnoInicioAtividade(),diretor.getId());
                }
            }
        }

        if(!idEncontrado){
            throw new IDNaoEncontradoException("diretor",id);
        }

        return null;
    }

}

