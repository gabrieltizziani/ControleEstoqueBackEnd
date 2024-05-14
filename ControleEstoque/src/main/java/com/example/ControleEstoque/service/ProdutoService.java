package com.example.ControleEstoque.service;


import com.example.ControleEstoque.model.Produto;
import com.example.ControleEstoque.repository.EntradaRepository;
import com.example.ControleEstoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements CrudProdutoService<Produto> {

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    EntradaRepository entradaRepository;

    @Override
    public List<Produto> listarProduto() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto editarProduto(Produto produto, Long idProduto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        if (produtoOptional.isPresent()) {
            Produto produtoExistente = produtoOptional.get();
            produtoExistente.setNomeProduto(produto.getNomeProduto());
            produtoExistente.setPrecoProduto(produto.getPrecoProduto());
            // Adicione outros atributos que você deseja editar

            return produtoRepository.save(produtoExistente);
        }
        return null; // Ou você pode lançar uma exceção indicando que o produto não foi encontrado
    }

    @Override
    public boolean excluirProduto(Long idProduto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            // Primeiro, exclua todas as entradas associadas a este produto
            entradaRepository.deleteByProduto(produto);
            // Agora é seguro excluir o produto
            produtoRepository.deleteById(idProduto);
            return true;
        }
        return false;
    }



}