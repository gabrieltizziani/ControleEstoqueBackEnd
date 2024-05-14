package com.example.ControleEstoque.repository;

import com.example.ControleEstoque.model.Funcionario;
import com.example.ControleEstoque.model.Produto;
import com.example.ControleEstoque.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;

public interface SaidaRepository extends JpaRepository<Saida, Long> {
    List<Saida> findAll();
    List<Saida> findByDataSaidaBetween(LocalDate dataInicial, LocalDate dataFinal);
    List<Saida> findByProduto(Produto produto);

    List<Saida> findByDataSaidaBetweenAndFuncionario(LocalDate dataInicial, LocalDate dataFinal, Funcionario funcionario);
}
