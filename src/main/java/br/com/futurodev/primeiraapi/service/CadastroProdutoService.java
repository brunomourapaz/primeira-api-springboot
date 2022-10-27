package br.com.futurodev.primeiraapi.service;


import br.com.futurodev.primeiraapi.model.Produto;
import br.com.futurodev.primeiraapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto) {
        // as ficariam aqui
        return produtoRepository.save(produto);
    }

    @Transactional
    public void deleteById(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    public Produto getProdutoById(Long idProduto) {
        return produtoRepository.findById(idProduto).get();
    }


    public List<Produto> getProdutosByDescricao(String descricao) {
        List<Produto> produtos = produtoRepository.getProdutosByDescricao(descricao);
        return produtos;
    }

    public List<Produto> getProdutos() {
        return (List<Produto>) produtoRepository.findAll();
    }
}
