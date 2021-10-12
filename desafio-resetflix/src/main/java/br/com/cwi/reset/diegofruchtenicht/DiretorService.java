package br.com.cwi.reset.diegofruchtenicht;

import exception.DadosObrigatoriosException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DiretorService {

    List<Diretor> diretores = new ArrayList<>();
    private FakeDatabase fakeDatabase;

    public DiretorService (FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }

    public void cadastrarDiretor (DiretorRequest diretorRequest) throws DadosObrigatoriosException {

        diretores = fakeDatabase.recuperaDiretores();
        LocalDate hoje = LocalDate.now();

        //exception campos nulos
        if(diretorRequest.getNome().equals(null) || diretorRequest.getNome().isEmpty()){
            throw new DadosObrigatoriosException("Campo obrigatório não informado. Favor informar o campo {Nome}.");
        }else if(diretorRequest.getDataNascimento() == null){
            throw new DadosObrigatoriosException("Campo obrigatório não informado. Favor informar o campo {Data Nascimento}.");
        }else if(diretorRequest.getAnoInicioAtividade() == 0){
            throw new DadosObrigatoriosException("Campo obrigatório não informado. Favor informar o campo {Ano Início Atividade}.");
        }

        // exception nome e sobrenome
        if ((diretorRequest.getNome().split(" ").length < 2)){
            throw new DadosObrigatoriosException("Deve ser informado no mínimo nome e sobrenome para o diretor");
        }

        // exception nome ja cadastrado
        for (Diretor diretor : diretores){
            if(diretor.getNome().equals(diretorRequest.getNome())) {
                throw new DadosObrigatoriosException("Já existe um diretor cadastrado para o nome {" + diretorRequest.getNome() + "}.");
            }
        }

        // exception Data de Nascimento
        if (diretorRequest.getDataNascimento().isAfter(hoje) ){
            throw new DadosObrigatoriosException("Não é possível cadastrar diretores não nascidos.");
        }

        // exception Inicio da Atividade
        if (diretorRequest.getAnoInicioAtividade()< diretorRequest.getDataNascimento().getYear() || diretorRequest.getAnoInicioAtividade() > hoje.getYear() ){
            throw new DadosObrigatoriosException("Ano de início de atividade inválido para o diretor cadastrado.");
        }

        //Obtem ultimo ID
        int lastID;

        if (diretores.size() > 0){
            lastID = diretores.get(diretores.size()-1).getId();
            lastID ++;
        }else{
            lastID = 0;
        }

        // Salva no Banco de Dados
        fakeDatabase.persisteDiretor(new Diretor (diretorRequest.getNome(),diretorRequest.getDataNascimento(),diretorRequest.getAnoInicioAtividade(),lastID));


    }

    public List listarDiretores (String filtroNome) throws DadosObrigatoriosException{

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
                    throw new DadosObrigatoriosException("Diretor não encontrato com o filtro {"+ filtroNome +"}, favor informar outro filtro.");
                }

            }

        }else{
            throw new DadosObrigatoriosException("Nenhum diretor cadastrado, favor cadastar diretores.");
        }

        return diretoresFiltrados;
    }

    public Diretor consultarDiretor (Integer id) throws DadosObrigatoriosException {

        diretores = fakeDatabase.recuperaDiretores();
        boolean idEncontrado = false;

        if (id == null){
            throw new DadosObrigatoriosException("Campo obrigatório não informado. Favor informar o campo {id}.");
        }else{
            for (Diretor diretor: diretores){
                if(diretor.getId()==id){
                    idEncontrado = true;
                    return new Diretor (diretor.getNome(),diretor.getDataNascimento(),diretor.getAnoInicioAtividade(),diretor.getId());
                }
            }
        }
        if(!idEncontrado){
            throw new DadosObrigatoriosException("Nenhum diretor encontrado com o parâmetro id={"+ id +"}, favor verifique os parâmetros informados.");
        }else{


        }

        return null;
    }

}

