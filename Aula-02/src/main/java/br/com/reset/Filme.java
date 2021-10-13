package br.com.reset;

public class Filme {

   private String nome;
   private String descricao;
   private int duracao;
   private int anoLancamento;
   private double avaliacao;
   private Diretor diretor;


    public Filme(String nome, String descricao, int duracao, int anoLancamento, double avaliacao, Diretor diretor) throws AvaliacaoForaLimitesException {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
        this.diretor = diretor;

        if (avaliacao < 1 || avaliacao > 5){
            throw new AvaliacaoForaLimitesException("Avaliação Fora dos Limites [1 a 5]");
        }
    }

    public void reproduzir (){
        System.out.println("*Nome :" + nome + " *Descrição: " + descricao + " *Duração: " + duracao + " min." + " *Diretor: " + diretor.getNome());
    }


}
