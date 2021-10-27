package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.repository.AtorRepository;
import br.com.cwi.reset.diegofruchtenicht.response.AtorEmAtividade;
import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Ator;
import br.com.cwi.reset.diegofruchtenicht.model.StatusCarreira;
import br.com.cwi.reset.diegofruchtenicht.request.AtorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    List<Ator> atores = new ArrayList<>();

    public void criarAtor (AtorRequest atorRequest) throws  NomeSobrenomeException, NomeJaCadastradoException, AnoInicioAtividadeException {
        atores = atorRepository.findAll();

        LocalDate hoje = LocalDate.now();

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

        // exception Inicio da Atividade
        if (atorRequest.getAnoInicioAtividade()< atorRequest.getDataNascimento().getYear() || atorRequest.getAnoInicioAtividade() > hoje.getYear() ){
            throw new AnoInicioAtividadeException("ator");
        }

        // Salva no Banco de Dados
        atorRepository.save(new Ator(atorRequest.getNome(),atorRequest.getDataNascimento(),atorRequest.getStatusCarreira(),atorRequest.getAnoInicioAtividade()));

    }

    public List listarAtoresEmAtividade (String filtroNome) throws  FiltroNaoEncontradoException, NaoCadastradoException, NenhumAtorAtividade {
      atores = atorRepository.findAll();

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

    public Ator consultarAtor (Integer id) throws  IDNaoEncontradoException {

        return atorRepository.findById(id).orElseThrow(() -> new IDNaoEncontradoException("ator",id));

   }

    public List consultarAtores () throws  NaoCadastradoException {
        atores = atorRepository.findAll();

       if(atores.size()>0){
           return atores;
       }else{
           throw new NaoCadastradoException("ator", "atores");
       }

   }

}
