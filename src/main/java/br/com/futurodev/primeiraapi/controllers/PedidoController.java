package br.com.futurodev.primeiraapi.controllers;

import br.com.futurodev.primeiraapi.dto.ItemPedidoRepresentationModel;
import br.com.futurodev.primeiraapi.dto.PedidoRepresentationModel;
import br.com.futurodev.primeiraapi.input.PedidoInput;
import br.com.futurodev.primeiraapi.model.ItemPedido;
import br.com.futurodev.primeiraapi.model.Pedido;
import br.com.futurodev.primeiraapi.model.Produto;
import br.com.futurodev.primeiraapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;

    @Autowired
    private CadastroClienteService cadastroClienteService;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private CadastroItemPedidoService cadastroItemPedidoService;


    @PostMapping
    public ResponseEntity<PedidoRepresentationModel> cadastrar(@RequestBody PedidoInput pedidoInput){
       Pedido pedido =  cadastroPedidoService.salvar(toDomainObject(pedidoInput));
       return new ResponseEntity<PedidoRepresentationModel>(toRepresentatioModel(pedido), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PedidoRepresentationModel> atualizar(@RequestBody PedidoInput pedidoInput){
        Pedido pedido = cadastroPedidoService.salvar(toDomainObject(pedidoInput));
        return  new ResponseEntity<PedidoRepresentationModel>(toRepresentatioModel(pedido),HttpStatus.OK);
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idPedido){
        cadastroPedidoService.deletePedidoById(idPedido);
        return new ResponseEntity<String>("Pedido de ID: "+idPedido+" deletado.", HttpStatus.OK);
    }

    @GetMapping(value = "/{idPedido}")
    public ResponseEntity<PedidoRepresentationModel> getPedidoById(@PathVariable(value = "idPedido") Long idPedido){

        PedidoRepresentationModel pedidoRepresentationModel =
                toRepresentatioModel(cadastroPedidoService.getPedidoById(idPedido));

        return new ResponseEntity<PedidoRepresentationModel>(pedidoRepresentationModel, HttpStatus.OK);

    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PedidoRepresentationModel>> getPedidos(){
        List<Pedido> pedidos = cadastroPedidoService.getPedidos();

        return
                new ResponseEntity<List<PedidoRepresentationModel>>(toCollectionRepresentationModel(pedidos), HttpStatus.OK);
    }

    @GetMapping(value = "/cliente/{idCliente}")
    public ResponseEntity<List<PedidoRepresentationModel>> getPedidoByIdCliente(
            @PathVariable(name = "idCliente") Long idCliente) {

        List<Pedido> pedidos = cadastroPedidoService.getPedidoByIdCliente(idCliente);
        List<PedidoRepresentationModel> pedidosRM = toCollectionRepresentationModel(pedidos);

        return new ResponseEntity<List<PedidoRepresentationModel>>(pedidosRM, HttpStatus.OK);
    }

    // TODO Falta ajuste neste método

    @DeleteMapping(value = "/{idPedido}/item/{idItemPedido}")
    @ResponseBody
    public ResponseEntity<String> deleteItemPedidoById(@PathVariable(name = "idPedido") Long idPedido,
            @PathVariable(name = "idItemPedido") Long idItemPedido){

      //ItemPedido itemPedido = cadastroItemPedidoService.getItemPedidoById(idItemPedido);
        ItemPedido itemPedido = cadastroItemPedidoService.getItemPedido(idPedido, idItemPedido);
      //cadastroItemPedidoService.deleteItemPedido(itemPedido);
        cadastroItemPedidoService.deleteItemPedidoById(itemPedido.getId());

      return new ResponseEntity<String>("Item de ID: "+idItemPedido+" deletado.",HttpStatus.OK);
    }



    //converte de Model para DTO de Reposta
    private PedidoRepresentationModel toRepresentatioModel(Pedido pedido){
        PedidoRepresentationModel pedidoRepresentationModel = new PedidoRepresentationModel();
        pedidoRepresentationModel.setId(pedido.getId());
        pedidoRepresentationModel.setIdCliente(pedido.getCliente().getId());
        pedidoRepresentationModel.setNomeCliente(pedido.getCliente().getNome());
        pedidoRepresentationModel.setIdFormaPagamento(pedido.getFormaPagamento().getId());
        pedidoRepresentationModel.setFormaPagamentoDescricao(pedido.getFormaPagamento().getDescricao());

        for (int i = 0; i < pedido.getItensPedido().size(); i++) {
            ItemPedidoRepresentationModel itemPedidoRepresentationModel = new ItemPedidoRepresentationModel();
            itemPedidoRepresentationModel.setId(pedido.getItensPedido().get(i).getId());
            itemPedidoRepresentationModel.setIdProduto(pedido.getItensPedido().get(i).getProduto().getId());
            itemPedidoRepresentationModel.setDescricaoProduto(pedido.getItensPedido().get(i).getProduto().getDescricao());
            itemPedidoRepresentationModel.setQuantidade(pedido.getItensPedido().get(i).getQuantidade());
            itemPedidoRepresentationModel.setValorItem(pedido.getItensPedido().get(i).getValorItem());

            pedidoRepresentationModel.getItensPedidoRepresentationModel().add(itemPedidoRepresentationModel);

        }



        return pedidoRepresentationModel;
    }


    //Converte de DTO de entrada para Model
    private Pedido toDomainObject(PedidoInput pedidoInput){

        Pedido pedido = new Pedido();

        pedido.setId(pedidoInput.getIdPedido());

        pedido.setCliente(cadastroClienteService.getClienteById(pedidoInput.getIdCliente()));
        pedido.setFormaPagamento(cadastroFormaPagamentoService.getFormaPagamentoById(pedidoInput.getIdFormaPagamento()));

        for (int i = 0; i < pedidoInput.getItensPedido().size(); i++) {

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setId(pedidoInput.getItensPedido().get(i).getId());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(cadastroProdutoService.getProdutoById(pedidoInput.getItensPedido().get(i).getIdProduto()));

            // se o ID do itemPedido for null busca o preço do cadastro de produto
            if(pedidoInput.getItensPedido().get(i).getId() == null) {
                itemPedido.setValorItem(cadastroProdutoService
                        .getProdutoById(pedidoInput
                                .getItensPedido().get(i)
                                .getIdProduto()).getPrecoVenda());
            // se não pega o preco do Json de entrada
            }else{
                itemPedido.setValorItem(pedidoInput.getItensPedido().get(i).getPrecoVenda());
            }

            itemPedido.setQuantidade(pedidoInput.getItensPedido().get(i).getQuantidade());


            // adiciono a lista de itensPedido do model
            pedido.getItensPedido().add(itemPedido);

        }


        return pedido;
    }

    private List<PedidoRepresentationModel> toCollectionRepresentationModel(List<Pedido> pedidos){
        return pedidos.stream()
                .map(Pedido -> toRepresentatioModel(Pedido))
                .collect(Collectors.toList());
    }




}
