package br.com.futurodev.primeiraapi.controllers;

import br.com.futurodev.primeiraapi.model.ProdutoModel;
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

    @Autowired // IC/CD/CDI Injeção de Depencias
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

        ProdutoModel produto = new ProdutoModel();
        produto.setDescricao(descricao);
        produtoRepository.save(produto); // grava no banco de dados um produto


        return "Produto "+descricao+" registrado com sucesso!";

    }

      @GetMapping(value = "/produtos")
      @ResponseBody // Retorna os dados no corpo da resposta
      public ResponseEntity<List<ProdutoModel>> listarProdutos(){

        List<ProdutoModel> produtos = produtoRepository.findAll(); // consulta no banco de dados todos os produtos

        return new ResponseEntity<List<ProdutoModel>>(produtos, HttpStatus.OK); // Retorna a lista em JSON

      }

}
