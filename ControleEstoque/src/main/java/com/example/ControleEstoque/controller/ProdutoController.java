package com.example.ControleEstoque.controller;

import com.example.ControleEstoque.model.Produto;
import com.example.ControleEstoque.repository.ProdutoRepository;
import com.example.ControleEstoque.service.ProdutoService;
import com.example.ControleEstoque.service.QuantidadeRealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Usada para marcar uma classe como um controller no padrão MVC (Model-View-Controller) do Spring.
@CrossOrigin("*")
@RequestMapping("/produtos") // Especifica a URL que um método do controlador irá manipular.
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;
    @Autowired
    private ProdutoRepository produtoRepository;



    @GetMapping
    public List<Produto> listarProduto(){
        return produtoService.listarProduto();
    }

    @PostMapping
    public Produto criarProduto(@Valid @RequestBody Produto produto) {
        return produtoService.criarProduto(produto);
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<?> editarProduto(@PathVariable Long idProduto, @RequestBody Produto produto){
        Produto produtoEditado = produtoService.editarProduto(produto, idProduto);
        if (produtoEditado != null) {
            return ResponseEntity.ok(produtoEditado);
        } else {
            String mensagem = "O produto com o ID " + idProduto + " não foi encontrado na base de dados.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<?> excluirProduto(@PathVariable Long idProduto){
        if(produtoService.excluirProduto(idProduto)){
            String mensagem = "A deleção do produto com o ID " + idProduto + " foi realizada com sucesso.";
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensagem);
        } else {
            String mensagem = "O produto com o ID " + idProduto + " não foi encontrado na base de dados.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
    }

    @GetMapping("/{nomeProduto}")
    public Optional<Produto> getProdutoPorNome(@PathVariable("nomeProduto") String nomeProduto) {
        return produtoRepository.findByNomeProduto(nomeProduto);
    }
}