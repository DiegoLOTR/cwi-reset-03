package br.com.reset;

public class Filme {

   private String nome;
   private String descricao;
   private int duracao;
   private int anoLancamento;
   private double avaliacao;
   private Diretor diretor;


    public Filme(String nome, String descricao, int duracao, int anoLancamento, double avaliacao, Diretor diretor) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
        this.diretor = diretor;
    }

    public void reproduzir (){
        System.out.println("*Nome :" + nome + " *Descrição: " + descricao + " *Duração: " + duracao + " min." + " *Diretor: " + diretor.getNomeDiretor());
    }


}
