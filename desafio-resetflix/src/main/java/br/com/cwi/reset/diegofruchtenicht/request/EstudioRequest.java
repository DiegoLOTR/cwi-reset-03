package br.com.cwi.reset.diegofruchtenicht.request;

import br.com.cwi.reset.diegofruchtenicht.model.StatusAtividade;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

public class EstudioRequest {

    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo Nome.")
   private String nome;
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo Descrição")
   private String descricao;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Data de Criação")
    @PastOrPresent (message = "Não é possível cadastrar estúdios do futuro.")
   private LocalDate dataCriacao;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Status Atividade")
   private StatusAtividade statusAtividade;

    public EstudioRequest(String nome, String descricao, LocalDate dataCriacao, StatusAtividade statusAtividade) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.statusAtividade = statusAtividade;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public StatusAtividade getStatusAtividade() {
        return statusAtividade;
    }
}
