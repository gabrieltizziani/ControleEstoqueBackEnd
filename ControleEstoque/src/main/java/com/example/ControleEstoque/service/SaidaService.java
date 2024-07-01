package com.example.ControleEstoque.service;

import com.example.ControleEstoque.model.Saida;
import com.example.ControleEstoque.model.Funcionario;
import com.example.ControleEstoque.model.Produto;
import com.example.ControleEstoque.repository.FuncionarioRepository;
import com.example.ControleEstoque.repository.ProdutoRepository;
import com.example.ControleEstoque.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaidaService implements CrudSaidaService<Saida> {
    @Autowired
    SaidaRepository saidaRepository;
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    QuantidadeRealService quantidadeRealService;

    @Override
    public List<Saida> listarSaida() {
        return saidaRepository.findAll();
    }

    @Override
    public Saida criarSaida(Saida saida) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findByNomeFuncionario(saida.getFuncionario().getNomeFuncionario());
        Optional<Produto> optionalProduto = produtoRepository.findByNomeProduto(saida.getProduto().getNomeProduto());

        if (optionalFuncionario.isPresent() && optionalProduto.isPresent()) {
            Funcionario funcionario = optionalFuncionario.get();
            Produto produto = optionalProduto.get();

            saida.setDataSaida(saida.getDataSaida());
            saida.setFuncionario(funcionario);
            saida.setProduto(produto);
            saida.setQuantidadeProduto(saida.getQuantidadeProduto());
            saida.setTipo(saida.getTipo());

            float valorTotal = calcularValorTotal(saida);
            saida.setValorTotal(valorTotal);

            Saida novaSaida = saidaRepository.save(saida);

            // Atualizar quantidade real do produto
            produto.setQuantidadeReal(quantidadeRealService.calcularQuantidadeReal(produto));
            produtoRepository.save(produto);

            return novaSaida;
        }
        return null;
    }

    @Override
    public Saida editarSaida(Saida saida, Long idSaida) {
        if (saidaRepository.existsById(idSaida)) {
            saida.setIdSaida(idSaida);
            Saida saidaEditada = saidaRepository.save(saida);

            // Atualizar quantidade real do produto
            Produto produto = saida.getProduto();
            produto.setQuantidadeReal(quantidadeRealService.calcularQuantidadeReal(produto));
            produtoRepository.save(produto);

            return saidaEditada;
        }
        return null;
    }

    @Override
    public boolean excluirSaida(Long idSaida) {
        if (saidaRepository.existsById(idSaida)) {
            Saida saida = saidaRepository.findById(idSaida).orElse(null);
            if (saida != null) {
                saidaRepository.deleteById(idSaida);

                // Atualizar quantidade real do produto
                Produto produto = saida.getProduto();
                produto.setQuantidadeReal(quantidadeRealService.calcularQuantidadeReal(produto));
                produtoRepository.save(produto);

                return true;
            }
        }
        return false;
    }

    @Override
    public float calcularValorTotal(Saida saida) {
        float precoProduto = saida.getProduto().getPrecoProduto();
        float quantidade = saida.getQuantidadeProduto();
        return precoProduto * quantidade;
    }
}
