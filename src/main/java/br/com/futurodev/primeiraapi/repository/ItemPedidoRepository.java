package br.com.futurodev.primeiraapi.repository;

import br.com.futurodev.primeiraapi.model.ItemPedido;
import br.com.futurodev.primeiraapi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {


    /*@Query("select p from Pedido p where p.cliente.id = ?1")
    List<Pedido> getPedidosByIdCliente(Long idCliente);*/

    @Query("select i from ItemPedido i where i.pedido.id = ?1 and i.id = ?2")
    ItemPedido getItemPedido(Long idPedido, Long idItemPedido);


}
