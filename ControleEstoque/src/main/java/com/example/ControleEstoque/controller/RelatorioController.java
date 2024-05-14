package com.example.ControleEstoque.controller;

import com.example.ControleEstoque.model.Entrada;
import com.example.ControleEstoque.model.Funcionario;
import com.example.ControleEstoque.model.Saida;
import com.example.ControleEstoque.repository.EntradaRepository;
import com.example.ControleEstoque.repository.FuncionarioRepository;
import com.example.ControleEstoque.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private EntradaRepository entradaRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/entradas")
    @CrossOrigin(origins = "http://localhost:5174")
    public ResponseEntity<Map<String, Object>> gerarRelatorioEntradas(
            @RequestParam("dataInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFinal) {

        LocalDate dataInicialLocalDate = dataInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFinalLocalDate = dataFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        List<Entrada> entradas = relatorioService.gerarRelatorioEntradasPorPeriodo(dataInicialLocalDate, dataFinalLocalDate);
        float valorTotal = relatorioService.calcularValorTotalEntradas(entradas);

        Map<String, Object> response = new HashMap<>();
        response.put("entradas", entradas);
        response.put("valorTotal", valorTotal);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/saidas")
    @CrossOrigin(origins = "http://localhost:5174")
    public ResponseEntity<Map<String, Object>> gerarRelatorioSaidas(
            @RequestParam("dataInicial") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicial,
            @RequestParam("dataFinal") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFinal,
            @RequestParam(value = "funcionario", required = false) Long idFuncionario) {

        LocalDate dataInicialLocalDate = dataInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataFinalLocalDate = dataFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Funcionario funcionario = null;
        if (idFuncionario != null) {
            funcionario = funcionarioRepository.findById(idFuncionario).orElse(null);
        }

        List<Saida> saidas = relatorioService.gerarRelatorioSaidasPorPeriodo(dataInicialLocalDate, dataFinalLocalDate, funcionario);
        float valorTotal = relatorioService.calcularValorTotalSaidas(saidas);

        Map<String, Object> response = new HashMap<>();
        response.put("saidas", saidas);
        response.put("valorTotal", valorTotal);
        return ResponseEntity.ok(response);
    }

}
