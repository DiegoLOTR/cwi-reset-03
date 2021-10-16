package br.com.cwi.reset.primeiroprojetospring.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Pessoa {

    private String nome;
    private LocalDate dataNascimento;
    private TipoGenero genero;

    public Pessoa(String nome, LocalDate dataNascimento, TipoGenero genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public TipoGenero getGenero() {
        return genero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setGenero(TipoGenero genero) {
        this.genero = genero;
    }

    public void imprimirDados(){
        System.out.println("*Nome :" + nome + " *Data Nasc.: " + dataNascimento + " *Genero: " + genero.getGenero() );
    }

    public void calcularIdade (){
        LocalDate hoje = LocalDate.now();

        long anos = this.dataNascimento.until(hoje, ChronoUnit.YEARS);

        System.out.println("A idade de " + nome +  " é: " + anos + " anos.");
    }
}
