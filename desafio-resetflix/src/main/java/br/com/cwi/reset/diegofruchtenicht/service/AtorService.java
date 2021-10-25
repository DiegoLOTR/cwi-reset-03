package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.response.AtorEmAtividade;
import br.com.cwi.reset.diegofruchtenicht.FakeDatabase;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Ator;
import br.com.cwi.reset.diegofruchtenicht.model.StatusCarreira;
import br.com.cwi.reset.diegofruchtenicht.request.AtorRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtorService {

    List<Ator> atores = new ArrayList<>();

    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void criarAtor (AtorRequest atorRequest) throws CamposObrigatoriosException, NomeSobrenomeException, NomeJaCadastradoException, AnoInicioAtividadeException, NaoNascidosException {
        atores = fakeDatabase.recuperaAtores();

        LocalDate hoje = LocalDate.now();

        //exception campos nulos
        if(atorRequest.getNome().equals(null) || atorRequest.getNome().isEmpty()){
            throw new CamposObrigatoriosException("Nome");
        }else if(atorRequest.getDataNascimento() == null){
            throw new CamposObrigatoriosException("Data Nascimento");
        }else if(atorRequest.getAnoInicioAtividade() == null){
            throw new CamposObrigatoriosException("Ano Início Atividade");
        }else if(atorRequest.getStatusCarreira() == null){
            throw new CamposObrigatoriosException("Status Carreira");
        }

       // exception nome e sobrenome
        if ((atorRequest.getNome().split(" ").length < 2)){
            throw new NomeSobrenomeException("ator");
        }

        // exception nome ja cadastrado
        for (Ator ator : atores){
            if(ator.getNome().equals(atorRequest.getNome())) {
                throw new NomeJaCadastradoException("ator",atorRequest.getNome());
            }
        }

        // exception Data de Nascimento
        if (atorRequest.getDataNascimento().isAfter(hoje) ){
            throw new NaoNascidosException("atores");
        }

        // exception Inicio da Atividade
        if (atorRequest.getAnoInicioAtividade()< atorRequest.getDataNascimento().getYear() || atorRequest.getAnoInicioAtividade() > hoje.getYear() ){
            throw new AnoInicioAtividadeException("ator");
        }

        //Obtem ultimo ID
        int ultimoId;

            if (atores.size() > 0){
                ultimoId = atores.get(atores.size()-1).getId();
                ultimoId ++;
            }else{
                ultimoId = 0;
            }

        // Salva no Banco de Dados
        fakeDatabase.persisteAtor(new Ator(atorRequest.getNome(),atorRequest.getDataNascimento(),atorRequest.getAnoInicioAtividade(),ultimoId,atorRequest.getStatusCarreira()));

    }

    public List listarAtoresEmAtividade (String filtroNome) throws CamposObrigatoriosException, FiltroNaoEncontradoException, NaoCadastradoException, NenhumAtorAtividade {
      atores = fakeDatabase.recuperaAtores();

      List <AtorEmAtividade> atorEmAtividade = new ArrayList<>();

      if(atores.size()>0){
          if(filtroNome.equals("") || filtroNome.equals(null)){
              for (int i=0 ; i < atores.size() ; i++){
                  if(atores.get(i).getStatusCarreira() == StatusCarreira.EM_ATIVIDADE){
                      AtorEmAtividade atorFiltradoAtividade = new AtorEmAtividade (atores.get(i).getId(),atores.get(i).getNome(),atores.get(i).getDataNascimento());
                      atorEmAtividade.add(atorFiltradoAtividade);
                  }
              }

              if(atorEmAtividade.size() <= 0){
                  throw new NenhumAtorAtividade();
              }

          }else{
              for(int i =0 ; i < atores.size() ; i++){
                  if(atores.get(i).getNome().contains(filtroNome) && (atores.get(i).getStatusCarreira() == StatusCarreira.EM_ATIVIDADE)){
                      AtorEmAtividade atorFiltradoNome = new AtorEmAtividade (atores.get(i).getId(),atores.get(i).getNome(),atores.get(i).getDataNascimento());
                      atorEmAtividade.add(atorFiltradoNome);
                  }
              }

              if(atorEmAtividade.size() <= 0){
                  throw new FiltroNaoEncontradoException("Ator",filtroNome );
              }
          }

      }else{
            throw new NaoCadastradoException("ator","atores");
      }

     return atorEmAtividade;

   }

    public Ator consultarAtor (Integer id) throws CamposObrigatoriosException, IDNaoEncontradoException {
        atores = fakeDatabase.recuperaAtores();

        boolean idEncontrado = false;

        if (id == null){
            throw new CamposObrigatoriosException("id");
        }else{
            for (Ator ator: atores){
                if(ator.getId()==id){
                 idEncontrado = true;
                 return new Ator (ator.getNome(),ator.getDataNascimento(),ator.getAnoInicioAtividade(),ator.getId(),ator.getStatusCarreira());
                }
            }
        }

        if(!idEncontrado){
            throw new IDNaoEncontradoException("ator",id);
        }

     return null;

   }

    public List consultarAtores () throws CamposObrigatoriosException, NaoCadastradoException {
       atores = fakeDatabase.recuperaAtores();

       if(atores.size()>0){
           return atores;
       }else{
           throw new NaoCadastradoException("ator", "atores");
       }

   }

}
