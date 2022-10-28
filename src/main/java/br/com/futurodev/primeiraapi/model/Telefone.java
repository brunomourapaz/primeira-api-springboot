package br.com.futurodev.primeiraapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "telefone")
public class Telefone implements Serializable {

   // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_telefone")
   // @SequenceGenerator(name = "seq_telefone", sequenceName = "seq_telefone_sequencia", allocationSize = 1, initialValue = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private String tipo;

    //private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id", foreignKey = @ForeignKey(name ="fk_usuario"))
    @JsonBackReference
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telefone that = (Telefone) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
