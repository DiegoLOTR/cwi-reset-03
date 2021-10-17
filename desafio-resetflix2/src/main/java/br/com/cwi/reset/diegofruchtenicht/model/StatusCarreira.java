package br.com.cwi.reset.diegofruchtenicht.model;

public enum StatusCarreira {

    EM_ATIVIDADE("Em Atividade"),
    APOSENTADO("Aposetado");

    private String status;

    StatusCarreira(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
