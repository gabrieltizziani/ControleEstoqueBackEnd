package com.example.ControleEstoque.service;

import com.example.ControleEstoque.model.Entrada;

import java.util.List;

public interface CrudEntradaService <E> {
    List<E> listarEntrada();
    E criarEntrada(E e);
    E editarEntrada(E E, Long idEntrada);
    boolean excluirEntrada(Long idEntrada);
    float calcularValorTotal(Entrada entrada);
}
