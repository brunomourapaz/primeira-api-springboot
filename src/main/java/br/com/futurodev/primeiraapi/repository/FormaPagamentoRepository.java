package br.com.futurodev.primeiraapi.repository;

import br.com.futurodev.primeiraapi.model.Cliente;
import br.com.futurodev.primeiraapi.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}
