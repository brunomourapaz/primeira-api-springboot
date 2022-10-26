package br.com.futurodev.primeiraapi.controllers;


import br.com.futurodev.primeiraapi.dto.ProdutoRepresentationModel;
import br.com.futurodev.primeiraapi.input.ProdutoInput;
import br.com.futurodev.primeiraapi.input.UsuarioInput;
import br.com.futurodev.primeiraapi.model.Produto;
import br.com.futurodev.primeiraapi.service.CadastroProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<ProdutoRepresentationModel> cadastrar(@RequestBody ProdutoInput produtoInput){
       // Produto pro = cadastroProdutoService.salvar(produto);
       // return new ResponseEntity<Produto>(pro, HttpStatus.CREATED);
        Produto produto = toDomainObject(produtoInput);
        cadastroProdutoService.salvar(produto);

        return new ResponseEntity<ProdutoRepresentationModel>(toModel(produto), HttpStatus.CREATED);
    }

    private Produto toDomainObject(ProdutoInput produtoInput){
        Produto produto = new Produto();
        produto.setId(produtoInput.getIdProduto());
        produto.setDescricao(produtoInput.getDescricao());
        produto.setPrecoCompra(produtoInput.getCompra());
        produto.setPrecoVenda(produtoInput.getVenda());
        return produto;
    }

    private ProdutoRepresentationModel toModel(Produto produto){
        ProdutoRepresentationModel produtoRepresentationModel = new ProdutoRepresentationModel();
        produtoRepresentationModel.setId(produto.getId());
        produtoRepresentationModel.setDecricao(produto.getDescricao());
        produtoRepresentationModel.setPrecoCompra(produto.getPrecoCompra());
        produtoRepresentationModel.setPrecoVenda(produto.getPrecoVenda());
        return produtoRepresentationModel;
    }

}
