package com.example.ControleEstoque.service;

import com.example.ControleEstoque.model.Produto;
import com.example.ControleEstoque.model.Entrada;
import com.example.ControleEstoque.model.Saida;
import com.example.ControleEstoque.repository.EntradaRepository;
import com.example.ControleEstoque.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantidadeRealService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private SaidaRepository saidaRepository;

    public int calcularQuantidadeReal(Produto produto) {
        List<Entrada> entradas = entradaRepository.findByProduto(produto);
        List<Saida> saidas = saidaRepository.findByProduto(produto);

        int quantidadeEntrada = calcularTotalEntradas(entradas);
        int quantidadeSaida = calcularTotalSaidas(saidas);

        return quantidadeEntrada - quantidadeSaida;
    }

    public float calcularValorProduto(Produto produto) {
        List<Entrada> entradas = entradaRepository.findByProduto(produto);
        float valorProduto = 0;

        for (Entrada entrada : entradas) {
            valorProduto += entrada.getQuantidadeProdutoEntrada() * produto.getPrecoProduto();
        }

        return valorProduto;
    }

    private int calcularTotalEntradas(List<Entrada> entradas) {
        int quantidadeTotal = 0;
        for (Entrada entrada : entradas) {
            quantidadeTotal += entrada.getQuantidadeProdutoEntrada();
        }
        return quantidadeTotal;
    }

    private int calcularTotalSaidas(List<Saida> saidas) {
        int quantidadeTotal = 0;
        for (Saida saida : saidas) {
            quantidadeTotal += saida.getQuantidadeProduto();
        }
        return quantidadeTotal;
    }
}
