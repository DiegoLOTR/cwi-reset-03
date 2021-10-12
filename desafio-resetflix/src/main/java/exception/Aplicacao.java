package exception;

import br.com.cwi.reset.diegofruchtenicht.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aplicacao {

        public static void main(String[] args) throws DadosObrigatoriosException {

        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);
        DiretorService diretorService = new DiretorService(fakeDatabase);

        // CRIAR ATORES
            System.out.println("********** ATORES **********");
            //exception nome nulo Ator
            //AtorRequest actorRequest = new AtorRequest("",LocalDate.of(1989,11,12),2021, StatusCarreira.APOSENTADO);
            //exception Data Nascimento nulo Ator
            //AtorRequest actorRequest = new AtorRequest("Joao Silva",null ,2021, StatusCarreira.APOSENTADO);
            //exception Ano Inicio nulo Ator
            //AtorRequest actorRequest = new AtorRequest("Joao Silva",LocalDate.of(1989,11,12),null, StatusCarreira.APOSENTADO);
            //exception Status Carreira nulo Ator
            //AtorRequest actorRequest = new AtorRequest("Joao Silva",LocalDate.of(1989,11,12),2021, null);
            //exception Nome e Sobrenome Ator
            //AtorRequest actorRequest = new AtorRequest("Joao",LocalDate.of(1989,11,12),2021, StatusCarreira.APOSENTADO);
            //exception Data Nascimento maior que data atual
            //AtorRequest actorRequest = new AtorRequest("Joao Silva",LocalDate.of(2021,11,12),2021, StatusCarreira.APOSENTADO);
            //exception Ano Inicio Maior anterior a data de Nascimento
            //AtorRequest actorRequest = new AtorRequest("Joao Silva",LocalDate.of(1989,11,12),1988, StatusCarreira.APOSENTADO);

           AtorRequest atorRequest = new AtorRequest("Joao M. Silva",LocalDate.of(1989,11,12),2021, StatusCarreira.EM_ATIVIDADE);
           atorService.criarAtor(atorRequest);
           AtorRequest atorRequest2 = new AtorRequest("Maria Jose",LocalDate.of(1989,11,12),2020, StatusCarreira.EM_ATIVIDADE);
           //exception Atores com mesmo nome;
           //AtorRequest atorRequest2 = new AtorRequest("Joao Silva",LocalDate.of(1989,11,12),2020, StatusCarreira.APOSENTADO);
            atorService.criarAtor(atorRequest2);


            List<Ator> atores = fakeDatabase.recuperaAtores();

//        System.out.println("Quantidade atores encontrada: " + atores.size());
//        System.out.println("-------------------------");
//        System.out.println("Primeiro ator: " + atores.get(0).getNome());
//        System.out.println("ID Primeiro Ator: " + atores.get(0).getId());
//        System.out.println("-------------------------");
//        System.out.println("Segundo ator: " + atores.get(1).getNome());
//        System.out.println("ID Segundo Ator: " + atores.get(1).getId());

        //BUSCA POR ATOR EM ATIVIDADE
            List<AtorEmAtividade> atorEmAtividade = new ArrayList<>();
            //Ator em Atividade SEM Filtro
            //atorEmAtividade = atorService.listarAtoresEmAtividade("");
            //Ator em Atividade COM Filtro
            atorEmAtividade = atorService.listarAtoresEmAtividade("Mar");
            System.out.println("-------------------------");
                 for (AtorEmAtividade ator : atorEmAtividade) {
                     System.out.println("Lista de Atores em Atividade: " + ator.getNome());
                 }

        // BUSCA ATOR POR ID
            Ator atorConsultado = atorService.consultarAtor(0);
            // exception ID inexistente
            //Ator atorConsultado = atorService.consultarAtor(10);
            //exeception ID nulo
            //Ator atorConsultado = atorService.consultarAtor(null);
            System.out.println("-------------------------");
            System.out.println("Ator Consultado por ID: " + atorConsultado.getNome());

        // BUSCA TODOS ATORES
            List<Ator> atoresCadastrados = atorService.consultarAtores();
            System.out.println("-------------------------");
            for (Ator ator : atoresCadastrados) {
                System.out.println("Lista de Atores ID: " + ator.getId());
                System.out.println("Lista de Atores Nome: " + ator.getNome());
                System.out.println("Lista de Atores Data Nascimento: " + ator.getDataNascimento());
                System.out.println("Lista de Atores Inicio Atividade: " + ator.getAnoInicioAtividade());
                System.out.println("Lista de Atores Status Carreira: " + ator.getStatusCarreira());
                System.out.println("-------------------------");
            }

        //CADASTRAR DIRETOR
            DiretorRequest diretorRequest = new DiretorRequest("Steven Spilberg",LocalDate.of(1960,1,1),1987) ;
            DiretorRequest diretorRequest2 = new DiretorRequest("Chirs Nolan",LocalDate.of(1972,1,1),1990) ;
            // exception nome nulo
           // DiretorRequest diretorRequest = new DiretorRequest("",LocalDate.of(1960,1,1),1987) ;
            // exception Ano Nascimento nulo
            //DiretorRequest diretorRequest = new DiretorRequest("Steven Spilberg",null,1987) ;
            // exception Ano Inicio Atividade nulo
            //DiretorRequest diretorRequest = new DiretorRequest("Steven Spilberg",LocalDate.of(1960,1,1),null) ;
            // exception nome e sobremone
            //DiretorRequest diretorRequest = new DiretorRequest("Paulo",LocalDate.of(1960,1,1),1987) ;
            // exception Data Nascimento maior que data atual
            //DiretorRequest diretorRequest = new DiretorRequest("Paulo Silva",LocalDate.of(2022,1,1),1989) ;
            // exception Ano Inicio menor que Data Nascimento
            //DiretorRequest diretorRequest = new DiretorRequest("Paulo Silva",LocalDate.of(1990,1,1),1989) ;
            // exception Diretor com mesmo nome
            //DiretorRequest diretorRequest = new DiretorRequest("Chirs Nolan",LocalDate.of(1972,1,1),1990) ;
            diretorService.cadastrarDiretor(diretorRequest);
            diretorService.cadastrarDiretor(diretorRequest2);

        //LISTAR DIRETORES
            System.out.println("********** DIRETORES **********");
            //Diretor SEM Filtro
            List <Diretor> listaDiretores = diretorService.listarDiretores("");
            //Diretor COM Filtro
            //List <Diretor> listaDiretores = diretorService.listarDiretores("N");
            for (Diretor diretor : listaDiretores) {
                System.out.println("Lista de Diretores ID: " + diretor.getId());
                System.out.println("Lista de Diretores Nome: " + diretor.getNome());
                System.out.println("Lista de Diretores Data Nascimento: " + diretor.getDataNascimento());
                System.out.println("Lista de Diretores Ano Inicio Atividade: " + diretor.getAnoInicioAtividade());
                System.out.println("-------------------------");
            }


        // BUSCA DIRETOR POR ID
            Diretor diretorConsultado = diretorService.consultarDiretor(1);
            // exception ID inexistente
            //Diretor diretorConsultado = diretorService.consultarDiretor(10);
            //exeception ID nulo
            //Diretor diretorConsultado = diretorService.consultarDiretor(null);
            System.out.println("Diretor Consultado por ID: " + diretorConsultado.getNome());
            System.out.println("-------------------------");









        }
}
