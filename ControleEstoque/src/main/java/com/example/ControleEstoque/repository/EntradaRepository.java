package com.example.ControleEstoque.repository;

import com.example.ControleEstoque.model.Entrada;
import com.example.ControleEstoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long> {
    List<Entrada> findAll();

    List<Entrada> findByDataEntradaBetween(LocalDate dataInicial, LocalDate dataFinal);
    List<Entrada> findByProduto(Produto produto);
    void deleteByProduto(Produto produto);
}
