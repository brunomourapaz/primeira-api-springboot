package br.com.futurodev.primeiraapi.service;

import br.com.futurodev.primeiraapi.model.UsuarioModel;
import br.com.futurodev.primeiraapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CasdastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

  @Transactional
    public UsuarioModel salvar(UsuarioModel usuario){
        //Aqui neste ponto seriam as regras de negócio
        //Outras operações como delete, update, insert
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Long idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }




    public UsuarioModel getUserById(Long idUsuario){
        return usuarioRepository.findById(idUsuario).get();
    }


    public List<UsuarioModel> getUserByName(String nome){
        return usuarioRepository.getUserByName(nome);
    }

    public List<UsuarioModel> getUsers(){
        return usuarioRepository.findAll();
    }

}
