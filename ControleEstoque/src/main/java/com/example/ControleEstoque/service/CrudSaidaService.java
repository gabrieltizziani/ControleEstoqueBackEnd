package com.example.ControleEstoque.service;


import com.example.ControleEstoque.model.Saida;

import java.util.List;

public interface CrudSaidaService <S> {
        List<S> listarSaida();
        S criarSaida(S s);
        S editarSaida(S s, Long idSaida);
        boolean excluirSaida(Long idSaida);
        float calcularValorTotal(Saida saida);

}
