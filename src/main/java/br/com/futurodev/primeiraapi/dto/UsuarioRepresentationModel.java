package br.com.futurodev.primeiraapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepresentationModel {

    private Long id;

   // @JsonIgnore //ignora o atributo na geração da represemtação de saída
    private String nome;
    private String login;

   // @JsonIgnore
    private String senha;

    private OffsetDateTime dataCadastro;

    private OffsetDateTime dataAtualizacao;


    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    @JsonIgnoreProperties(value = "tipo", allowGetters = true)
    private List<TelefoneRepresentationModel> telefones = new ArrayList<TelefoneRepresentationModel>();

    public List<TelefoneRepresentationModel> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneRepresentationModel> telefones) {
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public OffsetDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(OffsetDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public OffsetDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(OffsetDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
