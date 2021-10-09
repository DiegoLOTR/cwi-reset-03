package br.com.reset;

public class Aplicacao {

    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Joao",32, TipoGenero.MASCULINO, 2);

        Diretor diretor2 = new Diretor("Maria", 38,TipoGenero.FEMININO, 5);

        Ator ator1 = new Ator ("Viggo",25, TipoGenero.MASCULINO, 5);

        Filme filme1 = new Filme("Resgate Soldado Ryan", "Filme Segunda Guera Mundial", 240
                ,2008, 5.0,diretor1);

        Filme filme2 = new Filme("Lagoa Azul", "Filme Romance", 220
                ,1999, 4.2, diretor2);

        filme1.reproduzir();
        filme2.reproduzir();

        System.out.println("-------");

        ator1.imprimirDados();
        diretor1.imprimirDados();



    }

}
