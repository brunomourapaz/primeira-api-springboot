package br.com.futurodev.primeiraapi.controllers;

import br.com.futurodev.primeiraapi.model.Pedido;
import br.com.futurodev.primeiraapi.service.CadastroPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private CadastroPedidoService cadastroPedidoService;


    @PostMapping
    public ResponseEntity<Pedido> cadastrar(@RequestBody Pedido pedido){
        Pedido ped =  cadastroPedidoService.salvar(pedido);
       return new ResponseEntity<Pedido>(ped, HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Pedido>> getPedidos(){
        List<Pedido> pedidos = cadastroPedidoService.getPedidos();
        return new ResponseEntity<List<Pedido>>(pedidos, HttpStatus.OK);
    }



}
