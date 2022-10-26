package br.com.futurodev.primeiraapi.controllers;

import br.com.futurodev.primeiraapi.model.Produto;
import br.com.futurodev.primeiraapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {

    @Autowired //IC/ CD/ CDI Injeção de Dependência
    private ProdutoRepository produtoRepository;

    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarvalor/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }

    @GetMapping(value = "/mostarnome/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public String mostrarnome(@PathVariable String nome){
        return "Ola "+nome;
    }

    @RequestMapping(value = "/produto/{descricao}" , method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String salvar(@PathVariable String descricao){

        Produto produto = new Produto();
        produto.setDescricao(descricao);
        produtoRepository.save(produto); // grava no banco de dados um produto

        return "Produto "+descricao+" registrado com sucesso!";
    }

    /*
    @GetMapping(value = "/produtos")
    @ResponseBody // Retorna os dados no corpo da resposta
    public ResponseEntity<List<Produto>> listarProdutos(){

       // List<Produto> produtos = produtoRepository.findAll(); // consulta no banco de dados todos os produtos

        // return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK); // Retorna a lista em JSON
    }
    */


    @PostMapping(value = "/produto/salvar") /* Mapeia a URL */
    @ResponseBody /* Descreve a resposta informando que o retorno será no corpo da requisição */
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){ /* Recebe os dados para salvar */
          Produto prod = produtoRepository.save(produto);
          return new ResponseEntity<Produto>(prod, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/produto/delete") /* Mapeia a URL */
    @ResponseBody /* Descrição da resposta */
    public ResponseEntity<String> delete(@RequestParam Long idProduto){ /* Recebe da requisição o parâmetro */
           produtoRepository.deleteById(idProduto);
           return new ResponseEntity<String>("Produto deletado com sucesso.",HttpStatus.OK);
    }

    @GetMapping(value = "/produto/buscar/{id}")
    @ResponseBody
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        Produto prod = produtoRepository.findById(id).get();
        return new ResponseEntity<Produto>(prod, HttpStatus.OK);

    }


}
