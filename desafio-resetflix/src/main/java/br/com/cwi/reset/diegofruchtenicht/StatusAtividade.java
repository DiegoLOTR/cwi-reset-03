package br.com.cwi.reset.diegofruchtenicht;

public enum StatusAtividade {

    EM_ATIVIDADE("Em Atividade"),
    ENCERRADO("Encerrado");

    private String status;

    StatusAtividade(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
