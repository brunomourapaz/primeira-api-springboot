package br.com.futurodev.primeiraapi.controllers;


import br.com.futurodev.primeiraapi.dto.ProdutoRepresentationModel;
import br.com.futurodev.primeiraapi.input.ProdutoInput;
import br.com.futurodev.primeiraapi.input.UsuarioInput;
import br.com.futurodev.primeiraapi.model.Produto;
import br.com.futurodev.primeiraapi.service.CadastroProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Produtos")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @ApiOperation("Salvar um produto")
    @PostMapping
    public ResponseEntity<ProdutoRepresentationModel> cadastrar(@RequestBody ProdutoInput produtoInput) {
        Produto produto = toDomainObject(produtoInput);
        cadastroProdutoService.salvar(produto);
        return new ResponseEntity<ProdutoRepresentationModel>(toModel(produto), HttpStatus.CREATED);
    }

    @ApiOperation("Atualiza um produto")
    @PutMapping
    public ResponseEntity<ProdutoRepresentationModel> atualizar(@RequestBody ProdutoInput produtoInput) {
        Produto produto = cadastroProdutoService.salvar(toDomainObject(produtoInput));
        return new ResponseEntity<ProdutoRepresentationModel>(toModel(produto), HttpStatus.OK);
    }


    @ApiOperation("Deleta um produto")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(@ApiParam(value = "ID do produto", example = "1") @RequestParam Long idProduto) {
        cadastroProdutoService.deleteById(idProduto);
        return new ResponseEntity<String>("Produto ID: " + idProduto + ": deletado com sucesso!", HttpStatus.OK);

    }

    @ApiOperation("Busca um produto por ID")
    @GetMapping(value = "/produto/{idProduto}")
    public ResponseEntity<ProdutoRepresentationModel> getProdutoById(@ApiParam(value = "Id do produto", example = "1")
                                                                     @PathVariable(value = "idProduto") Long idProduto) {
        ProdutoRepresentationModel produtoRepresentationModel = toModel(cadastroProdutoService.getProdutoById(idProduto));
        return new ResponseEntity<ProdutoRepresentationModel>(produtoRepresentationModel, HttpStatus.OK);
    }


    @ApiOperation("Busca produtos por descrição")
    @GetMapping(value = "/produto")
    public ResponseEntity<List<ProdutoRepresentationModel>> getProdutosByName(
            @RequestParam(name = "descricao") String descricao) {
        // obtem a lista de produtos do Model, nossas entidades
        List<Produto> produtos = cadastroProdutoService.getProdutosByDescricao(descricao.toUpperCase());
        // convertemos o lista Model para Representation Model nosso DTO de saída
        List<ProdutoRepresentationModel> produtosRepresentationModel = toCollectionModel(produtos);
        return new ResponseEntity<List<ProdutoRepresentationModel>>(produtosRepresentationModel, HttpStatus.OK);
    }


    @ApiOperation("Lista produtos")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProdutoRepresentationModel>> getProdutos() {

        List<Produto> produtos = cadastroProdutoService.getProdutos();

        // convertemos o lista Model para Representation Model nosso DTO de saída
        List<ProdutoRepresentationModel> produtosRepresentationModel = toCollectionModel(produtos);

        return new ResponseEntity<List<ProdutoRepresentationModel>>(produtosRepresentationModel, HttpStatus.OK);


    }


    private Produto toDomainObject(ProdutoInput produtoInput) {
        Produto produto = new Produto();
        produto.setId(produtoInput.getIdProduto());
        produto.setDescricao(produtoInput.getDescricao());
        produto.setPrecoCompra(produtoInput.getCompra());
        produto.setPrecoVenda(produtoInput.getVenda());
        return produto;
    }

    private ProdutoRepresentationModel toModel(Produto produto) {
        ProdutoRepresentationModel produtoRepresentationModel = new ProdutoRepresentationModel();
        produtoRepresentationModel.setId(produto.getId());
        produtoRepresentationModel.setDecricao(produto.getDescricao());
        produtoRepresentationModel.setPrecoCompra(produto.getPrecoCompra());
        produtoRepresentationModel.setPrecoVenda(produto.getPrecoVenda());
        return produtoRepresentationModel;
    }

    // Converte uma lista de objetos do tipo Produto para uma lista de objetos do tipo ProdutoRepresentationModel
    private List<ProdutoRepresentationModel> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(Produto -> toModel(Produto))
                .collect(Collectors.toList());

    }

}
