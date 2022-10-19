package br.com.futurodev.primeiraapi.repository;

import br.com.futurodev.primeiraapi.model.UsuarioModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {

    @Query(value = "select u from UsuarioModel u where u.nome like %?1%")
    ArrayList<UsuarioModel> getUserByName(String nome);

}
