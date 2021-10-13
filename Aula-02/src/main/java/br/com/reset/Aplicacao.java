package br.com.reset;

import java.time.LocalDate;

public class Aplicacao {

    public static void main(String[] args) throws AvaliacaoForaLimitesException {

        Diretor diretor1 = new Diretor("Joao", LocalDate.of(1989,05,6), TipoGenero.MASCULINO, 2);

        Diretor diretor2 = new Diretor("Maria", LocalDate.of(1989,12,31),TipoGenero.FEMININO, 5);

        Ator ator1 = new Ator ("Viggo",LocalDate.of(2009,03,02), TipoGenero.MASCULINO, 5);

        Filme filme1 = new Filme("Resgate Soldado Ryan", "Filme Segunda Guera Mundial", 240
                ,2008, 5.0,diretor1);

        Filme filme2 = new Filme("Lagoa Azul", "Filme Romance", 220
                ,1999, 4.2, diretor2);

        filme1.reproduzir();
        filme2.reproduzir();

        System.out.println("-------");

        ator1.imprimirDados();
        diretor1.imprimirDados();

        System.out.println("-------");
        diretor1.calcularIdade();
        diretor2.calcularIdade();
        ator1.calcularIdade();

    }

}
