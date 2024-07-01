package com.example.ControleEstoque.controller;

import com.example.ControleEstoque.model.Produto;
import com.example.ControleEstoque.repository.ProdutoRepository;
import com.example.ControleEstoque.service.QuantidadeRealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*") // Permitindo acesso de qualquer origem
@RequestMapping("/quantidadeReal")
public class QuantidadeRealController {
    @Autowired
    private QuantidadeRealService quantidadeRealService;
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/{idProduto}")
    public ResponseEntity<?> getQuantidadeRealProduto(@PathVariable Long idProduto) {
        Optional<Produto> optionalProduto = produtoRepository.findById(idProduto);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            float quantidadeReal = quantidadeRealService.calcularQuantidadeReal(produto);
            float valorProduto = quantidadeRealService.calcularValorProduto(produto);

            // Criar um objeto para retornar a quantidade real e o valor total
            QuantidadeValorResponse response = new QuantidadeValorResponse(quantidadeReal, valorProduto);

            return ResponseEntity.ok(response);
        } else {
            String mensagem = "O produto com o ID " + idProduto + " não foi encontrado.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
    }


    private static class QuantidadeValorResponse {
        private final float quantidadeReal;
        private final float valorProduto;

        public QuantidadeValorResponse(float quantidadeReal, float valorProduto) {
            this.quantidadeReal = quantidadeReal;
            this.valorProduto = valorProduto;
        }

        public float getQuantidadeReal() {
            return quantidadeReal;
        }

        public float getValorProduto() {
            return valorProduto;
        }
    }
}
