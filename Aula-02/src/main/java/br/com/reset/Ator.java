package br.com.reset;

public class Ator extends Pessoa {

   private int nrOscarVencido;

    public Ator(String nome, int idade, TipoGenero genero, int nrOscarVencido) {
        super (nome,idade, genero);
        this.nrOscarVencido = nrOscarVencido;
    }

   public int getNrOscarVencido() {
      return nrOscarVencido;
    }
}
