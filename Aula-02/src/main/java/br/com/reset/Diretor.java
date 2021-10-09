package br.com.reset;

public class Diretor {
    private String nomeDiretor;
    private int idadeDiretor;
    private int qtdFilmesDiretor;

    public Diretor(String nomeDiretor, int idadeDiretor, int qtdFilmesDiretor) {
        this.nomeDiretor = nomeDiretor;
        this.idadeDiretor = idadeDiretor;
        this.qtdFilmesDiretor = qtdFilmesDiretor;
    }

    public String getNomeDiretor() {
        return nomeDiretor;
    }

    public int getIdadeDiretor() {
        return idadeDiretor;
    }

    public int getQtdFilmesDiretor() {
        return qtdFilmesDiretor;
    }


}
