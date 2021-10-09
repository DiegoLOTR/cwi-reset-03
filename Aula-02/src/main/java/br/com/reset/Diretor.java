package br.com.reset;

public class Diretor {
    private String nome;
    private int idade;
    private int qtdFilmes;
    private TipoGenero genero;

    public Diretor(String nome, int idade, int qtdFilmes, TipoGenero genero) {
        this.nome = nome;
        this.idade = idade;
        this.qtdFilmes = qtdFilmes;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public int getQtdFilmes() {
        return qtdFilmes;
    }

    public void imprimirDados(){
        System.out.println("*Nome :" + nome + " *Idade: " + idade + " *Genero: " + genero.getGenero() );
    }

}
