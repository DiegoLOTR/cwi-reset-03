package br.com.reset;

public class Aplicacao {

    public static void main(String[] args) {

        Diretor diretor1 = new Diretor("Steven Sp.", 58,17);

        Diretor diretor2 = new Diretor("Joao.", 38,1);

        Filme filme1 = new Filme("Resgate Solda Ryan", "Filme Segunda Guera Mundial", 240
                ,2008, 5.0,diretor1);

        Filme filme2 = new Filme("Lagoa Azul", "Filme Romance", 220
                ,1999, 4.2, diretor2);

        filme1.reproduzir();
        filme2.reproduzir();

    }

}
