package br.com.reset;

public class Aplicacao {

    public static void main(String[] args) {
        Filme filme1 = new Filme("Resgate Solda Ryan", "Filme Segunda Guera Mundial", 240
                ,"2008", 5, "Steven Sp.", 58,17);

        Filme filme2 = new Filme("Lagoa Azul", "Filme Romance", 220
                ,"1999", 5, "Joao da Silva.", 38,1);

        filme1.reproduzir();
        filme2.reproduzir();

    }

}
