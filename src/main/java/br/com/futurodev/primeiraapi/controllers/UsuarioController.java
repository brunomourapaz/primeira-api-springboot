package br.com.futurodev.primeiraapi.controllers;

import br.com.futurodev.primeiraapi.dto.TelefoneRepresentationModel;
import br.com.futurodev.primeiraapi.dto.UsuarioRepresentationModel;
import br.com.futurodev.primeiraapi.input.UsuarioInput;
import br.com.futurodev.primeiraapi.model.Telefone;
import br.com.futurodev.primeiraapi.model.Usuario;
import br.com.futurodev.primeiraapi.service.CadastroUsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://app.floripa.com:9000", "http://app.saopaulo.com:9000"})
@Api(tags = "Usuários")
@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private CadastroUsuarioService casdastroUsuarioService;


   /* @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioModel> cadastrar(@RequestBody UsuarioModel usuario){
        UsuarioModel usu = cadastroUsuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioRepresentationModel>(toModel(usu), HttpStatus.CREATED);
    }*/

    @ApiOperation("Salva um usuário")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioRepresentationModel> cadastrar(@RequestBody @Valid UsuarioInput usuarioInput){
        // converte UsuarioInput em UsuarioModel
        Usuario usu = toDomainObject(usuarioInput);
        // chama nosso service para salvar o UsuarioModel no banco de dados
        casdastroUsuarioService.salvar(usu);

        // montamos o retorno com a reposta da requisição, convertemos UsuarioModel em RepresentationModel
        return new ResponseEntity<UsuarioRepresentationModel>(toModel(usu), HttpStatus.CREATED);

    }


 /*   @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioRepresentationModel> atualizar(@RequestBody UsuarioModel usuario){
        UsuarioModel usu = cadastroUsuarioService.salvar(usuario);
        return new ResponseEntity<UsuarioRepresentationModel>(toModel(usu), HttpStatus.OK);

    }*/

    @ApiOperation("Atualiza um usuário")
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<UsuarioRepresentationModel> atualizar(@RequestBody UsuarioInput usuarioInput){
        Usuario usuario = casdastroUsuarioService.salvar(toDomainObject(usuarioInput));
        return new ResponseEntity<UsuarioRepresentationModel>(toModel(usuario), HttpStatus.OK);

    }


    @ApiOperation("Deleta um usuário")
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String> delete(@ApiParam(value = "ID do usuário", example = "1") @RequestParam Long idUsuario){
        casdastroUsuarioService.delete(idUsuario);
        return new ResponseEntity<String>("Usuário deletado com sucesso!",HttpStatus.OK);
    }


 /*   @GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioModel> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){
        UsuarioModel usu =  casdastroUsuarioService.getUserById(idUsuario);
        return new ResponseEntity<UsuarioModel>(usu, HttpStatus.OK);
    }*/

    @ApiOperation("Busca um usuário por ID")
    @GetMapping(value = "/{idUsuario}", produces = "application/json")
    public ResponseEntity<UsuarioRepresentationModel> getUserById(@PathVariable(value = "idUsuario") Long idUsuario){
        Usuario usu =  casdastroUsuarioService.getUserById(idUsuario);

        UsuarioRepresentationModel usuarioRepresentationModel = toModel(usu);

        return new ResponseEntity<UsuarioRepresentationModel>(usuarioRepresentationModel, HttpStatus.OK);

    }

    @ApiOperation("Busca usuários por nome")
    @GetMapping(value = "/buscarPorNome", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UsuarioRepresentationModel>> getUserByName(@RequestParam (name = "nome") String nome){
        // obtem a lista de usuario do tipo Model, nossas entidades
        List<Usuario> usuarios = casdastroUsuarioService.getUserByName(nome);

        // nos convertemos o objeto do tipo UsuarioModel para RepresentationModel (DTO)
        List<UsuarioRepresentationModel> usuariosRepresentationModel = toCollectionModel(usuarios);
        // System.out.println(usuariosRepresentationModel.get(0).getDataCadastro());
        return new ResponseEntity<List<UsuarioRepresentationModel>>(usuariosRepresentationModel,HttpStatus.OK);
    }


    /*
      Adiciona no cabeçalho da resposta ACCESS_CONTROL_ALLOW_ORIGIN de forma manual
     */
    /*
    @ApiOperation("Listar usuários")
    @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UsuarioModel>> getUsers(){
        // obtem a lista de usuario do tipo Model, nossas entidades
        List<UsuarioModel> usuarios = casdastroUsuarioService.getUsers();

        return ResponseEntity.ok()
                .header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://app.floripa.com:9000")
                .body(usuarios);
    }*/



    /*
       Adiciona no cabeçalho da resposta ACCESS_CONTROL_ALLOW_ORIGIN com @CrosOrigin
     */


    @CrossOrigin
    @ApiOperation("Listar usuários")
    @GetMapping(value = "", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<UsuarioRepresentationModel>> getUsers(){
        // obtem a lista de usuario do tipo Model, nossas entidades
        List<Usuario> usuarios = casdastroUsuarioService.getUsers();

        // nos convertemos o objeto do tipo UsuarioModel para RepresentationModel (DTO)
        List<UsuarioRepresentationModel> usuariosRepresentationModel = toCollectionModel(usuarios);
        return new ResponseEntity<List<UsuarioRepresentationModel>>(usuariosRepresentationModel,HttpStatus.OK);
    }


    // converte um objeto do tipo UsuarioModel para um objeto do tipo RepresentationModel
    private UsuarioRepresentationModel toModel(Usuario usu) {

        UsuarioRepresentationModel usuarioRepresentationModel = new UsuarioRepresentationModel();
        usuarioRepresentationModel.setId(usu.getId());
        usuarioRepresentationModel.setNome(usu.getNome());
        usuarioRepresentationModel.setLogin(usu.getLogin());
        usuarioRepresentationModel.setSenha(usu.getSenha());
        // usuarioRepresentationModel.setDataCadastro(usu.getDataCadastro());
        //usuarioRepresentationModel.setDataAtualizacao(usu.getDataAtualizacao());


        // itera nos telefones do usuario e converter para DTOs
        for (int i=0; i<usu.getTelefones().size(); i++){

            TelefoneRepresentationModel telefoneRepresentationModel = new TelefoneRepresentationModel();
            telefoneRepresentationModel.setTipo(usu.getTelefones().get(i).getTipo());
            telefoneRepresentationModel.setNumero(usu.getTelefones().get(i).getNumero());
            telefoneRepresentationModel.setId(usu.getTelefones().get(i).getId());

            usuarioRepresentationModel.getTelefones().add(telefoneRepresentationModel);
        }

        return usuarioRepresentationModel;
    }

    // Converte uma lista de objetos do tipo UsuarioModel para uma lista de objetos do tipo UsuarioRepresentationModel
    private List<UsuarioRepresentationModel> toCollectionModel(List<Usuario> usuariosModel){
        return usuariosModel.stream()
                .map(usuarioModel -> toModel(usuarioModel))
                .collect(Collectors.toList());

    }


    // Converter um objeto do tipo UsuarioInput para UsuarioModel
    private Usuario toDomainObject(UsuarioInput usuarioInput){

        Usuario usuario = new Usuario();
        usuario.setId(usuarioInput.getId());
        usuario.setNome(usuarioInput.getNome());
        usuario.setLogin(usuarioInput.getLogin());
        usuario.setSenha(usuarioInput.getSenha());



        for (int i=0; i<usuarioInput.getTelefones().size(); i++){
            Telefone telefone = new Telefone();
            telefone.setTipo(usuarioInput.getTelefones().get(i).getTipo());
            telefone.setNumero(usuarioInput.getTelefones().get(i).getNumero());
            telefone.setId(usuarioInput.getTelefones().get(i).getId());
            telefone.setUsuario(usuario);

            usuario.getTelefones().add(telefone);

        }


        return usuario;

    }
}