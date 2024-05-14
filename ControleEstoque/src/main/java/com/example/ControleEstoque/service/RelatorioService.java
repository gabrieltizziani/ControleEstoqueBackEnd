package com.example.ControleEstoque.service;

import com.example.ControleEstoque.model.Entrada;
import com.example.ControleEstoque.model.Funcionario;
import com.example.ControleEstoque.model.Saida;
import com.example.ControleEstoque.repository.EntradaRepository;
import com.example.ControleEstoque.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private SaidaRepository saidaRepository;

    public List<Entrada> gerarRelatorioEntradasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return entradaRepository.findByDataEntradaBetween(dataInicial, dataFinal);
    }

    public List<Saida> gerarRelatorioSaidasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal, Funcionario funcionario) {
        if (funcionario != null) {
            return saidaRepository.findByDataSaidaBetweenAndFuncionario(dataInicial, dataFinal, funcionario);
        } else {
            return saidaRepository.findByDataSaidaBetween(dataInicial, dataFinal);
        }
    }

    public float calcularValorTotalEntradas(List<Entrada> entradas) {
        float total = 0;
        for (Entrada entrada : entradas) {
            total += entrada.getValorTotal();
        }
        return total;
    }

    public float calcularValorTotalSaidas(List<Saida> saidas) {
        float total = 0;
        for (Saida saida : saidas) {
            total += saida.getValorTotal();
        }
        return total;
    }
}
