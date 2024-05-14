package com.example.ControleEstoque.service;

import java.util.List;

public interface CrudFuncionarioService <T>{
    List<T> listarFuncionario();
    T criarFuncionario(T t);
    T editarFuncionario(T t, Long idFuncionario);
    boolean excluirFuncionario(Long idFuncionario);
}
