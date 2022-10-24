package br.com.futurodev.primeiraapi.repository;

import br.com.futurodev.primeiraapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> { //CrudRepository

    //@Query("select u.userName from User u inner join u.area ar where ar.idArea = :idArea")
    //@Query(value = "select p from PedidoModel p inner join p.cliente c where c.nome like %?1%")

    @Query(value = "select u from Usuario u where u.nome like %?1%")
    ArrayList<Usuario> getUserByName(String nome);

    @Query(value = "select u from Usuario u where u.login = ?1")
    Usuario findUserByLogin(String login);

}
