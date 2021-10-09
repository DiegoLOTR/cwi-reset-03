package br.com.reset;

public class Ator {
    private String nome;
    private int idade;
    private int nrOscarVencido;
    private TipoGenero genero;

    public Ator(String nome, int idade, int nrOscarVencido, TipoGenero genero) {
        this.nome = nome;
        this.idade = idade;
        this.nrOscarVencido = nrOscarVencido;
        this.genero = genero;
    }

    public void imprimirDados(){
            System.out.println("*Nome :" + nome + " *Idade: " + idade + " *Genero: " + genero.getGenero() );
    }
}
