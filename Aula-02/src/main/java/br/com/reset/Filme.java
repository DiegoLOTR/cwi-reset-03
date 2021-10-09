package br.com.reset;

public class Filme {

   private String nome;
   private String descricao;
   private int duracao;
   private String anoLancamento;
   private int avaliacao;
   private String nomeDiretor;
   private int idadeDiretor;
   private int qtdFilmesDiretor;

    public Filme(String nome, String descricao, int duracao, String anoLancamento, int avaliacao, String nomeDiretor, int idadeDiretor, int qtdFilmesDiretor) {
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
        this.nomeDiretor = nomeDiretor;
        this.idadeDiretor = idadeDiretor;
        this.qtdFilmesDiretor = qtdFilmesDiretor;
    }

    public void reproduzir (){
        System.out.println("*Nome :" + nome + " *Descrição: " + descricao + " *Duração: " + duracao + " min." + " *Diretor: " + nomeDiretor);
    }


}
