package com.example.ControleEstoque.repository;

import com.example.ControleEstoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNomeProduto(String nomeProduto);

}
