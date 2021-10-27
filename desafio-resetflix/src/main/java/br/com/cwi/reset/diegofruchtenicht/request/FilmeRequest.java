package br.com.cwi.reset.diegofruchtenicht.request;

import br.com.cwi.reset.diegofruchtenicht.model.Genero;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class FilmeRequest {


    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo Nome.")
    private String nome;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Ano de Lançamento")
    private Integer anoLancamento;
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo Capa do Filme")
    private String capaFilme;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Gêneros")
    private List<Genero> generos;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo ID do Diretor")
    private Integer idDiretor;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo ID do Estúdio")
    private Integer idEstudio;
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo Resumo")
    private String resumo;
    private List<@Valid PersonagemRequest> personagens;

    public FilmeRequest(String nome, Integer anoLancamento, String capaFilme, List<Genero> generos, Integer idDiretor, Integer idEstudio, String resumo, List<PersonagemRequest> personagens) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.capaFilme = capaFilme;
        this.generos = generos;
        this.idDiretor = idDiretor;
        this.idEstudio = idEstudio;
        this.resumo = resumo;
        this.personagens = personagens;
    }


    public String getNome() {
        return nome;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public String getCapaFilme() {
        return capaFilme;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public Integer getIdDiretor() {
        return idDiretor;
    }

    public Integer getIdEstudio() {
        return idEstudio;
    }

    public String getResumo() {
        return resumo;
    }

    public List<PersonagemRequest> getPersonagens() {
        return personagens;
    }
}
