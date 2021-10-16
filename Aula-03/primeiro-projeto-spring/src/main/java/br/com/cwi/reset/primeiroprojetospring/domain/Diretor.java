package br.com.cwi.reset.primeiroprojetospring.domain;

import java.time.LocalDate;

public class Diretor extends Pessoa {

   private int qtdFilmes;

    public Diretor(String nome, LocalDate dataNascimento, TipoGenero genero, int qtdFilmes) {
        super (nome,dataNascimento, genero);
        this.qtdFilmes = qtdFilmes;
    }

    public int getQtdFilmes() {
      return qtdFilmes;
    }
}
