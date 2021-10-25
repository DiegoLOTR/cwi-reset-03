package br.com.cwi.reset.diegofruchtenicht.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DadosPessoas {

    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo Nome.")
   private String nome;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Data de Nascimento.")
   private LocalDate dataNascimento;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Ano de Início da Atividade.")
   private Integer anoInicioAtividade;


    public DadosPessoas(String nome, LocalDate dataNascimento, Integer anoInicioAtividade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.anoInicioAtividade = anoInicioAtividade;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public Integer getAnoInicioAtividade() {
        return anoInicioAtividade;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setAnoInicioAtividade(Integer anoInicioAtividade) {
        this.anoInicioAtividade = anoInicioAtividade;
    }
}
