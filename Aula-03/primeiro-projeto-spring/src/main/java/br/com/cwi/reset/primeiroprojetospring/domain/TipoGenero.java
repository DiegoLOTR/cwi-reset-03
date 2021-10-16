package br.com.cwi.reset.primeiroprojetospring.domain;

public enum TipoGenero {
    MASCULINO ("Masculino"),
    FEMININO ("Feminino"),
    NAO_BINARIO ("Não Binario");

    private String descricao;

    TipoGenero(String genero) {
        this.descricao = genero;
    }

    public String getGenero() {
        return descricao;
    }
}
