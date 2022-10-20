package br.com.futurodev.primeiraapi.repository;

import br.com.futurodev.primeiraapi.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> { //CrudRepository

    @Query(value = "select u from UsuarioModel u where u.nome like %?1%")
    ArrayList<UsuarioModel> getUserByName(String nome);

}
