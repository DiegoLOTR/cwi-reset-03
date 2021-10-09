package br.com.reset;

public class Pessoa {

    private String nome;
    private int idade;
    private TipoGenero genero;

    public Pessoa(String nome, int idade, TipoGenero genero) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public void imprimirDados(){
        System.out.println("*Nome :" + nome + " *Idade: " + idade + " *Genero: " + genero.getGenero() );
    }
}
