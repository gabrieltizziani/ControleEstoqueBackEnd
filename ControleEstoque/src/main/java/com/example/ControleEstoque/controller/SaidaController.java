package com.example.ControleEstoque.controller;

import com.example.ControleEstoque.model.Saida;
import com.example.ControleEstoque.service.SaidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/saida")
public class SaidaController {
@Autowired
    SaidaService saidaService;
@GetMapping
@CrossOrigin(origins = "http://localhost:5174")
    public List<Saida> listarSaida(){
    return saidaService.listarSaida();}

    @PostMapping
    @CrossOrigin(origins = "http://localhost:5174")
    public Saida criarSaida(@Valid @RequestBody Saida saida) {
        return saidaService.criarSaida(saida);
    }

    @PutMapping("/{idSaida}")
    @CrossOrigin(origins = "http://localhost:5174")
    public ResponseEntity<?> editarSaida(@PathVariable Long idSaida, @RequestBody Saida saida){
        if(saidaService.editarSaida(saida, idSaida) == null){
            String mensagem = "o id informado não existe na base de dados";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        return ResponseEntity.ok(saida);
    }

    @DeleteMapping("/{idSaida}")
    @CrossOrigin(origins = "http://localhost:5174")
    public ResponseEntity<?> excluirSaida(@PathVariable Long idSaida) {
        if (saidaService.excluirSaida(idSaida)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O id informado não existe na base de dados.");
        }
    }
}

