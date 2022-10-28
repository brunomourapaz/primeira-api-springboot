package br.com.futurodev.primeiraapi.repository;

import br.com.futurodev.primeiraapi.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
