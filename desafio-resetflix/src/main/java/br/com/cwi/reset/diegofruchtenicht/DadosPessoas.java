package br.com.cwi.reset.diegofruchtenicht;

import java.time.LocalDate;

public class DadosPessoas {

   private String nome;
   private LocalDate dataNascimento;
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

    public int getAnoInicioAtividade() {
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
