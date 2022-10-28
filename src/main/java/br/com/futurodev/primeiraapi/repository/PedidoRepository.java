package br.com.futurodev.primeiraapi.repository;

import br.com.futurodev.primeiraapi.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {


}
