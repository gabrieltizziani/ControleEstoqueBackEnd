package com.example.ControleEstoque.controller;

import com.example.ControleEstoque.model.Entrada;
import com.example.ControleEstoque.model.Produto;
import com.example.ControleEstoque.repository.EntradaRepository;
import com.example.ControleEstoque.repository.ProdutoRepository;
import com.example.ControleEstoque.service.EntradaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*") // Permitindo acesso de qualquer origem
@RequestMapping("/entrada")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @GetMapping
    public List<Entrada> listarEntrada() {
        return entradaService.listarEntrada();
    }

    @PostMapping

    public ResponseEntity<Entrada> criarEntrada(@Valid @RequestBody Entrada entrada) {
        Entrada novaEntrada = entradaService.criarEntrada(entrada);
        if (novaEntrada != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(novaEntrada);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{idEntrada}")
    public ResponseEntity<?> editarEntrada(@PathVariable Long idEntrada, @Valid @RequestBody Entrada entrada) {
        Entrada entradaEditada = entradaService.editarEntrada(entrada, idEntrada);
        if (entradaEditada != null) {
            return ResponseEntity.ok(entradaEditada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id informado não existe na base de dados.");
        }
    }

    @DeleteMapping("/{idEntrada}")
    public ResponseEntity<?> excluirEntrada(@PathVariable Long idEntrada) {
        if (entradaService.excluirEntrada(idEntrada)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id informado não existe na base de dados.");
        }
    }
}
