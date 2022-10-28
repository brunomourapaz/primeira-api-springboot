package br.com.futurodev.primeiraapi.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PedidoInput {

    private Long idPedido;

    private Long idCliente;

    private Long idFormaPagamento;

    private List<ItemPedidoInput> itensPedido;
}
