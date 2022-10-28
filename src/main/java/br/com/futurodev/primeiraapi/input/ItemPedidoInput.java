package br.com.futurodev.primeiraapi.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ItemPedidoInput {

    private Long id;

    private Long idPedido;

    private Long idProduto;

    private double quantidade;

    private double precoVenda;


}
