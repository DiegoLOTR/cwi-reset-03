package br.com.cwi.reset.diegofruchtenicht;

public enum TipoAtuacao {

    PRINCIPAL("Principal"),
    COADJUVANTE("Coadjuvante");

    private String atucao;

    TipoAtuacao(String atucao) {
        this.atucao = atucao;
    }

    public String getAtucao() {
        return atucao;
    }
}