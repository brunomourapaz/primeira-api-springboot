package br.com.futurodev.primeiraapi.repository;


import br.com.futurodev.primeiraapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

    /*
      @Query(value = "select u from Usuario u where u.login = ?1")
    Usuario findUserByLogin(String login);
     */

    @Query("select p from Produto p where p.descricao like %?1%")
    List<Produto> getProdutosByDescricao(String descricao);


}
