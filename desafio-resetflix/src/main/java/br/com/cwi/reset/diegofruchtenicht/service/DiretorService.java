package br.com.cwi.reset.diegofruchtenicht.service;

import br.com.cwi.reset.diegofruchtenicht.exception.*;
import br.com.cwi.reset.diegofruchtenicht.model.Diretor;
import br.com.cwi.reset.diegofruchtenicht.repository.DiretorRepository;
import br.com.cwi.reset.diegofruchtenicht.request.DiretorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository diretorRepository;

    List<Diretor> diretores = new ArrayList<>();

    public void cadastrarDiretor (DiretorRequest diretorRequest) throws  NomeSobrenomeException, NomeJaCadastradoException, AnoInicioAtividadeException {
        diretores = diretorRepository.findAll();

        LocalDate hoje = LocalDate.now();

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

        // exception Inicio da Atividade
        if (diretorRequest.getAnoInicioAtividade()< diretorRequest.getDataNascimento().getYear() || diretorRequest.getAnoInicioAtividade() > hoje.getYear() ){
            throw new AnoInicioAtividadeException("diretor");
        }


        // Salva no Banco de Dados
        diretorRepository.save(new Diretor (diretorRequest.getNome(),diretorRequest.getDataNascimento(),diretorRequest.getAnoInicioAtividade()));

    }

    public List listarDiretores (String filtroNome) throws  FiltroNaoEncontradoException, NaoCadastradoException {
        diretores = diretorRepository.findAll();

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

    public Diretor consultarDiretor (Integer id) throws  IDNaoEncontradoException {

        return diretorRepository.findById(id).orElseThrow(() -> new IDNaoEncontradoException("diretor",id));

    }

}

