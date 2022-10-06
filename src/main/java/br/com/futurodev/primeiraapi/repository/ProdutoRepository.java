package br.com.futurodev.primeiraapi.repository;


import br.com.futurodev.primeiraapi.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {


}
