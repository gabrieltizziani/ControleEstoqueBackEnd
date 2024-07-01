package com.example.ControleEstoque.service;

import com.example.ControleEstoque.model.Entrada;
import com.example.ControleEstoque.model.Produto;
import com.example.ControleEstoque.repository.EntradaRepository;
import com.example.ControleEstoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService implements CrudEntradaService<Entrada>{
    @Autowired
    EntradaRepository entradaRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    QuantidadeRealService quantidadeRealService;

    @Override
    public List<Entrada> listarEntrada(){
        return entradaRepository.findAll();
    }

    @Override
    public Entrada criarEntrada(Entrada entrada) {
        Optional<Produto> optionalProduto = produtoRepository.findByNomeProduto(entrada.getProduto().getNomeProduto());

        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();

            entrada.setDataEntrada(entrada.getDataEntrada());
            entrada.setProduto(produto);
            entrada.setQuantidadeProdutoEntrada(entrada.getQuantidadeProdutoEntrada());
            entrada.setTipo(entrada.getTipo());
            entrada.setFornecedor(entrada.getFornecedor());
            entrada.setNotaFiscal(entrada.getNotaFiscal());

            // Calcular o valor total da entrada
            float valorTotal = calcularValorTotal(entrada);
            entrada.setValorTotal(valorTotal);

            Entrada novaEntrada = entradaRepository.save(entrada);

            // Atualizar quantidade real do produto
            produto.setQuantidadeReal(quantidadeRealService.calcularQuantidadeReal(produto));
            produtoRepository.save(produto);

            return novaEntrada;
        }
        return null;
    }

    @Override
    public Entrada editarEntrada(Entrada entrada, Long idEntrada) {
        if (entradaRepository.existsById(idEntrada)) {
            entrada.setIdEntrada(idEntrada);
            Entrada entradaEditada = entradaRepository.save(entrada);

            // Atualizar quantidade real do produto
            Produto produto = entrada.getProduto();
            produto.setQuantidadeReal(quantidadeRealService.calcularQuantidadeReal(produto));
            produtoRepository.save(produto);

            return entradaEditada;
        }
        return null;
    }

    @Override
    public boolean excluirEntrada(Long idEntrada) {
        if (entradaRepository.existsById(idEntrada)) {
            Entrada entrada = entradaRepository.findById(idEntrada).orElse(null);
            if (entrada != null) {
                entradaRepository.deleteById(idEntrada);

                // Atualizar quantidade real do produto
                Produto produto = entrada.getProduto();
                produto.setQuantidadeReal(quantidadeRealService.calcularQuantidadeReal(produto));
                produtoRepository.save(produto);

                return true;
            }
        }
        return false;
    }

    @Override
    public float calcularValorTotal(Entrada entrada) {
        float precoProduto = entrada.getProduto().getPrecoProduto();
        float quantidade = entrada.getQuantidadeProdutoEntrada();
        return precoProduto * quantidade;
    }
}
