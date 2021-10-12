package exception;

import br.com.cwi.reset.diegofruchtenicht.*;

import java.time.LocalDate;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) {

    FakeDatabase fakeDatabase = new FakeDatabase();

    AtorService atorService = new AtorService(fakeDatabase);

    AtorRequest actorRequest = new AtorRequest("kk",LocalDate.of(1989,11,12),1989, StatusCarreira.APOSENTADO);

    atorService.criarAtor(actorRequest);

        List<Ator> atores = fakeDatabase.recuperaAtores();

        System.out.println("Deve conter 1 ator, quantidade encontrada: " + atores.size());
        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(0).getId());

    }
}
