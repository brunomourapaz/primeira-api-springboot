package br.com.futurodev.primeiraapi.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    // @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String login;

    private String senha;

    private String nome;


    @CreationTimestamp
    @Column(columnDefinition = "timestamp(0) without time zone DEFAULT timezone('utc'::text, CURRENT_TIMESTAMP(0))", updatable = false)
    private OffsetDateTime dataCadastro;


    @UpdateTimestamp
    @Column(columnDefinition = "timestamp(0) without time zone DEFAULT timezone('utc'::text, CURRENT_TIMESTAMP(0))")
    private OffsetDateTime dataAtualizacao;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TelefoneModel> telefones = new ArrayList<TelefoneModel>();


    public List<TelefoneModel> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<TelefoneModel> telefones) {
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioModel that = (UsuarioModel) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
