package br.com.reset;

public class Diretor extends Pessoa {

   private int qtdFilmes;

    public Diretor(String nome, int idade, TipoGenero genero, int qtdFilmes) {
        super (nome,idade, genero);
        this.qtdFilmes = qtdFilmes;
    }

    public int getQtdFilmes() {
      return qtdFilmes;
    }
}
