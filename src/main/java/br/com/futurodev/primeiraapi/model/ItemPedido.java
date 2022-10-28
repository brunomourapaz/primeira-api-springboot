package br.com.futurodev.primeiraapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter

@Entity
@Table
public class ItemPedido implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valorItem;

    private double quantidade;

    @OneToOne
    @JoinColumn(name = "id_produto", foreignKey = @ForeignKey(name = "fk_produto"))
    private Produto produto;



    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id", foreignKey = @ForeignKey(name =  "fk_pedido"))
    // @JsonBackReference
    private Pedido pedido;


}
