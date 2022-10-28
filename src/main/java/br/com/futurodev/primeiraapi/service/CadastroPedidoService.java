package br.com.futurodev.primeiraapi.service;


import br.com.futurodev.primeiraapi.model.Pedido;
import br.com.futurodev.primeiraapi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

   @Transactional
    public Pedido salvar(Pedido pedido){
       Pedido ped = pedidoRepository.saveAndFlush(pedido);
        return ped;
    }

    @Transactional
    public void deletePedidoById(Long idPedido){
        pedidoRepository.deleteById(idPedido);
    }

    public Pedido getPedidoById(Long idPedido){
        return  pedidoRepository.findById(idPedido).get();
    }

    public List<Pedido> getPedidos(){
        return pedidoRepository.findAll();
    }

}
