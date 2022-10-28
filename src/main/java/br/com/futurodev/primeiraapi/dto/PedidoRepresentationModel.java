package br.com.futurodev.primeiraapi.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PedidoRepresentationModel {

    private Long id;

    private Long idCliente;

    private String nomeCliente;

    private Long idFormaPagamento;

    private String formaPagamentoDescricao;


    private List<ItemPedidoRepresentationModel> itensPedidoRepresentationModel = new ArrayList<ItemPedidoRepresentationModel>();

}
