package br.com.futurodev.primeiraapi.service;

import br.com.futurodev.primeiraapi.model.ItemPedido;
import br.com.futurodev.primeiraapi.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public void deleteItemPedidoById(Long idItemPedido){
        itemPedidoRepository.deleteById(idItemPedido);
    }


    public ItemPedido getItemPedidoById(Long idItemPedido){
        return itemPedidoRepository.findById(idItemPedido).get();
    }

    @Transactional
    public void deleteItemPedido(ItemPedido itemPedido){
        itemPedidoRepository.delete(itemPedido);
    }

    public ItemPedido getItemPedido(Long idPedido, Long idItemPedido){
       return itemPedidoRepository.getItemPedido(idPedido, idItemPedido);
    }


}
