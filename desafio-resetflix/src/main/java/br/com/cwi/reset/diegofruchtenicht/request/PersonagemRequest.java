package br.com.cwi.reset.diegofruchtenicht.request;

import br.com.cwi.reset.diegofruchtenicht.model.TipoAtuacao;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PersonagemRequest {

    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo ID do Ator")
    private Integer idAtor;
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo Nome do Personagem")
    private String nomePersonagem;
    @NotEmpty(message = "Campo obrigatório não informado. Favor informar o campo Descrição do Personagem")
    private String descricaoPersonagem;
    @NotNull(message = "Campo obrigatório não informado. Favor informar o campo Tipo de Atuação")
    private TipoAtuacao tipoAtuacao;

    public PersonagemRequest(Integer idAtor, String nomePersonagem, String descricaoPersonagem, TipoAtuacao tipoAtuacao) {
        this.idAtor = idAtor;
        this.nomePersonagem = nomePersonagem;
        this.descricaoPersonagem = descricaoPersonagem;
        this.tipoAtuacao = tipoAtuacao;
    }

    public Integer getIdAtor() {
        return idAtor;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public String getDescricaoPersonagem() {
        return descricaoPersonagem;
    }

    public TipoAtuacao getTipoAtuacao() {
        return tipoAtuacao;
    }
}
