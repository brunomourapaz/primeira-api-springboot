package br.com.futurodev.primeiraapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter

@Entity
@Table
public class ItemPedido implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private double valorItem;

    @Column(nullable = false)
    private double quantidade;

    @OneToOne
    @JoinColumn(name = "id_produto", foreignKey = @ForeignKey(name = "fk_produto"))
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id", foreignKey = @ForeignKey(name =  "fk_pedido"))
    private Pedido pedido;

    @JsonBackReference
    public Pedido getPedido() {
        return pedido;
    }
}
