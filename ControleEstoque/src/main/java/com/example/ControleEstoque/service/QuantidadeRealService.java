package com.example.ControleEstoque.service;

import com.example.ControleEstoque.model.Entrada;
import com.example.ControleEstoque.model.Produto;
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

    public float calcularQuantidadeReal(Produto produto) {
        List<Entrada> entradas = entradaRepository.findByProduto(produto);
        List<Saida> saidas = saidaRepository.findByProduto(produto);

        float quantidadeEntrada = calcularTotalEntradas(entradas);
        float quantidadeSaida = calcularTotalSaidas(saidas);

        return quantidadeEntrada - quantidadeSaida;
    }

    public float calcularValorProduto(Produto produto) {
        List<Entrada> entradas = entradaRepository.findByProduto(produto);
        List<Saida> saidas = saidaRepository.findByProduto(produto);

        float valorProduto = 0;

        for (Entrada entrada : entradas) {
            valorProduto += entrada.getQuantidadeProdutoEntrada() * produto.getPrecoProduto();
        }

        for (Saida saida : saidas) {
            valorProduto -= saida.getQuantidadeProduto() * produto.getPrecoProduto();
        }

        return valorProduto;
    }

    private float calcularTotalEntradas(List<Entrada> entradas) {
        float quantidadeTotal = 0;
        for (Entrada entrada : entradas) {
            quantidadeTotal += entrada.getQuantidadeProdutoEntrada();
        }
        return quantidadeTotal;
    }

    private float calcularTotalSaidas(List<Saida> saidas) {
        float quantidadeTotal = 0;
        for (Saida saida : saidas) {
            quantidadeTotal += saida.getQuantidadeProduto();
        }
        return quantidadeTotal;
    }
}
