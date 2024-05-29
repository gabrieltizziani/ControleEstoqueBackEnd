package com.example.ControleEstoque.controller;

import com.example.ControleEstoque.model.Funcionario;
import com.example.ControleEstoque.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin("*")
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired // é usada para realizar a injeção de dependência automática em beans gerenciados pelo contêiner Spring
    FuncionarioService funcionarioService;

    @GetMapping

    public List<Funcionario> listarFuncionario() {
        return funcionarioService.listarFuncionario();
    }


    @PostMapping

    public Funcionario criarFuncionario(@Valid @RequestBody Funcionario funcionario) {
        return funcionarioService.criarFuncionario(funcionario);
    }

    @PutMapping("/{idFuncionario}")
    public ResponseEntity<?> editarFuncionario(@PathVariable Long idFuncionario, @RequestBody Funcionario funcionario) {
        if (funcionarioService.editarFuncionario(funcionario, idFuncionario) == null) {
            String mensagem = " o id informado nao existe na base de dados";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("/{idFuncionario}")
    public ResponseEntity<?> excluirFuncionario(@PathVariable Long idFuncionario) {
        if (funcionarioService.excluirFuncionario(idFuncionario)) {
            String mensagem = "A deleção do id: " + idFuncionario + " foi realizada com sucesso.";
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensagem);
        }
        String mensagem = " o id informado nao existe na base de dados";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }
}
