package com.example.ControleEstoque.controller;

import com.example.ControleEstoque.repository.EntradaRepository;
import com.example.ControleEstoque.repository.FuncionarioRepository;
import com.example.ControleEstoque.repository.ProdutoRepository;
import com.example.ControleEstoque.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/quantidade")
public class QuantidadeController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private SaidaRepository saidaRepository;

    @GetMapping("/produtos")
    public Long getQuantidadeProdutos(){
        return produtoRepository.count();
    }

    @GetMapping("/funcionarios")
    public Long getQuantidadeFuncionarios(){
        return funcionarioRepository.count();
    }

    @GetMapping("/entradas")
    public Long getQuantidadeEntradas(){
        return entradaRepository.count();
    }
    @GetMapping("/saidas")
    public Long getQuantidadeSaidas(){
        return saidaRepository.count();
    }
}
