package br.com.futurodev.primeiraapi.repository;

import br.com.futurodev.primeiraapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


}
