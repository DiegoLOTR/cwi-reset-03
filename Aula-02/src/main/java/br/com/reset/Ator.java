package br.com.reset;

import java.time.LocalDate;

public class Ator extends Pessoa {

   private int nrOscarVencido;

    public Ator(String nome, LocalDate dataNascimento, TipoGenero genero, int nrOscarVencido) {
        super (nome,dataNascimento, genero);
        this.nrOscarVencido = nrOscarVencido;
    }

   public int getNrOscarVencido() {
      return nrOscarVencido;
    }
}
