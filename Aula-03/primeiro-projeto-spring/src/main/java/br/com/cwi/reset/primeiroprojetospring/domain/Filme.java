package br.com.cwi.reset.primeiroprojetospring.domain;

public class Filme {

   private String nome;
   private String descricao;
   private int duracao;
   private int anoLancamento;
   private double avaliacao;
   private Diretor diretor;


    public Filme(String nome, String descricao, int duracao, int anoLancamento, double avaliacao, Diretor diretor){
        this.nome = nome;
        this.descricao = descricao;
        this.duracao = duracao;
        this.anoLancamento = anoLancamento;
        this.avaliacao = avaliacao;
        this.diretor = diretor;

    }

    public void reproduzir (){
        System.out.println("*Nome :" + nome + " *Descrição: " + descricao + " *Duração: " + duracao + " min." + " *Diretor: " + diretor.getNome());
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }
}
