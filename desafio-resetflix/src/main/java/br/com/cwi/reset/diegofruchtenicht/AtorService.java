package br.com.cwi.reset.diegofruchtenicht;

import exception.DadosObrigatoriosException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AtorService {

    List<Ator> atores = new ArrayList<>();
    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }


    public void criarAtor (AtorRequest atorRequest) throws DadosObrigatoriosException {

        atores = fakeDatabase.recuperaAtores();
        LocalDate hoje = LocalDate.now();

        //exception campos nulos
        if(atorRequest.getNome().equals(null) || atorRequest.getNome().isEmpty()){
            throw new DadosObrigatoriosException("Campo obrigatório não informado. Favor informar o campo {Nome}.");
        }else if(atorRequest.getDataNascimento() == null){
            throw new DadosObrigatoriosException("Campo obrigatório não informado. Favor informar o campo {Data Nascimento}.");
        }else if(atorRequest.getAnoInicioAtividade() == 0){
            throw new DadosObrigatoriosException("Campo obrigatório não informado. Favor informar o campo {Ano Início Atividade}.");
        }else if(atorRequest.getStatusCarreira() == null){
            throw new DadosObrigatoriosException("Campo obrigatório não informado. Favor informar o campo {Status Carreira}.");
        }

       // exception nome e sobrenome
        if ((atorRequest.getNome().split(" ").length < 2)){
            throw new DadosObrigatoriosException("Deve ser informado no mínimo nome e sobrenome para o ator");
        }

        // exception nome ja cadastrado
        for (Ator ator : atores){
            if(ator.getNome().equals(atorRequest.getNome())) {
                throw new DadosObrigatoriosException("Já existe um ator cadastrado para o nome {" + atorRequest.getNome() + "}.");
            }
        }

        // exception Data de Nascimento
        if (atorRequest.getDataNascimento().isAfter(hoje) ){
            throw new DadosObrigatoriosException("Não é possível cadastrar atores não nascidos.");
        }

        // exception Inicio da Atividade
        if (atorRequest.getAnoInicioAtividade()< atorRequest.getDataNascimento().getYear() || atorRequest.getAnoInicioAtividade() > hoje.getYear() ){
            throw new DadosObrigatoriosException("Ano de início de atividade inválido para o ator cadastrado.");
        }

        //Obtem ultimo ID
        int lastID;

            if (atores.size() > 0){
                lastID = atores.get(atores.size()-1).getId();
                lastID ++;
            }else{
                lastID = 0;
            }

        // Salva no Banco de Dados
        fakeDatabase.persisteAtor(new Ator(atorRequest.getNome(),atorRequest.getDataNascimento(),atorRequest.getAnoInicioAtividade(),lastID,atorRequest.getStatusCarreira()));


    }

   public List listarAtoresEmAtividade (String filtroNome) throws DadosObrigatoriosException{

      atores = fakeDatabase.recuperaAtores();
      List <AtorEmAtividade> atorEmAtividade = new ArrayList<>();

      if(atores.size()>0){

          if(filtroNome.equals("")){

              for (int i=0 ; i < atores.size() ; i++){

                  if(atores.get(i).getStatusCarreira() == StatusCarreira.EM_ATIVIDADE){
                      AtorEmAtividade auxAtorEmAtividade = new AtorEmAtividade ();
                      auxAtorEmAtividade.setId(atores.get(i).getId());
                      auxAtorEmAtividade.setNome(atores.get(i).getNome());
                      auxAtorEmAtividade.setDataNascimento(atores.get(i).getDataNascimento());

                      atorEmAtividade.add(auxAtorEmAtividade);
                  }
              }

              if(atorEmAtividade.size() <= 0){
                  throw new DadosObrigatoriosException("Nenhum ator em Atividade");
              }

          }else{

              for(int i =0 ; i < atores.size() ; i++){

                  if(atores.get(i).getNome().contains(filtroNome) && (atores.get(i).getStatusCarreira() == StatusCarreira.EM_ATIVIDADE)){
                      AtorEmAtividade auxAtorEmAtividade2 = new AtorEmAtividade ();
                      auxAtorEmAtividade2.setId(atores.get(i).getId());
                      auxAtorEmAtividade2.setNome(atores.get(i).getNome());
                      auxAtorEmAtividade2.setDataNascimento(atores.get(i).getDataNascimento());

                      atorEmAtividade.add(auxAtorEmAtividade2);
                  }
              }

              if(atorEmAtividade.size() <= 0){
                  throw new DadosObrigatoriosException("Ator não encontrato com o filtro {"+ filtroNome +"}, favor informar outro filtro.");
              }
          }

      }else{
            throw new DadosObrigatoriosException("Nenhum ator cadastrado, favor cadastar atores.");
      }

     return atorEmAtividade;
    }

   public Ator consultarAtor (Integer id) throws DadosObrigatoriosException {

        atores = fakeDatabase.recuperaAtores();
        boolean idEncontrado = false;

        if (id == null){
            throw new DadosObrigatoriosException("Campo obrigatório não informado. Favor informar o campo {id}.");
        }else{
            for (Ator ator: atores){
                if(ator.getId()==id){
                 idEncontrado = true;
                 return new Ator (ator.getNome(),ator.getDataNascimento(),ator.getAnoInicioAtividade(),ator.getId(),ator.getStatusCarreira());
                }
            }
        }
            if(!idEncontrado){
                throw new DadosObrigatoriosException("Nenhum ator encontrado com o parâmetro id={"+ id +"}, favor verifique os parâmetros informados.");
            }else{


        }

     return null;
   }

   public List consultarAtores () throws DadosObrigatoriosException {

       atores = fakeDatabase.recuperaAtores();

       if(atores.size()>0){

           return atores;

       }else{
           throw new DadosObrigatoriosException("Nenhum ator cadastrado, favor cadastar atores.");
       }

   }





}
