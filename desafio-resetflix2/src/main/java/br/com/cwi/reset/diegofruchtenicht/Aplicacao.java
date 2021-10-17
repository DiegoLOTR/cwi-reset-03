package br.com.cwi.reset.diegofruchtenicht;
import br.com.cwi.reset.diegofruchtenicht.exception.CamposObrigatoriosException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aplicacao {

        public static void main(String[] args) throws CamposObrigatoriosException {

            SpringApplication.run(Aplicacao.class, args);

       }
}

