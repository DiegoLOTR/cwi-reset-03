package br.com.reset;

public class Aplicacao {

    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Steven Sp.", 58,17,TipoGenero.MASCULINO);

        Diretor diretor2 = new Diretor("Maria", 38,1, TipoGenero.FEMININO);

        Ator ator1 = new Ator ("Viggo",51,14,TipoGenero.MASCULINO);

        Filme filme1 = new Filme("Resgate Soldado Ryan", "Filme Segunda Guera Mundial", 240
                ,2008, 5.0,diretor1);

        Filme filme2 = new Filme("Lagoa Azul", "Filme Romance", 220
                ,1999, 4.2, diretor2);

        filme1.reproduzir();
        filme2.reproduzir();

        System.out.println("-------");

        ator1.imprimirDados();
        diretor2.imprimirDados();



    }

}
