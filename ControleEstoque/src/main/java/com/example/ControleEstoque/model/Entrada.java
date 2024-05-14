package com.example.ControleEstoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrada;

    @NotNull(message = "O campo data não pode ser nulo")
    @Column(nullable = false)
    private LocalDate dataEntrada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nomeProduto", referencedColumnName = "nomeProduto")
    private Produto produto;

    @NotNull(message = "O campo quantidade não pode ser nulo.")
    @Column(nullable = false)
    private int quantidadeProdutoEntrada;

    @NotNull(message = "O campo tipo não pode ser nulo.")
    @Column(nullable = false)
    private String tipo;  // Pode ser 'Unidade' ou 'Metro'

    @NotNull(message = "O campo tipo não pode ser nulo.")
    @Column(nullable = false)
    private  String fornecedor;

    @Column(nullable = false)
    private float valorTotal;

    @Column (nullable = false)
    private int notaFiscal;

    public Entrada() {
    }

    public Entrada(Long idEntrada, LocalDate dataEntrada, Produto produto, int quantidadeProdutoEntrada, String tipo, String fornecedor, float valorTotal, int notaFiscal) {
        this.idEntrada = idEntrada;
        this.dataEntrada = dataEntrada;
        this.produto = produto;
        this.quantidadeProdutoEntrada = quantidadeProdutoEntrada;
        this.tipo = tipo;
        this.fornecedor = fornecedor;
        this.valorTotal = valorTotal;
        this.notaFiscal = notaFiscal;
    }

    public Long getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(Long idEntrada) {
        this.idEntrada = idEntrada;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidadeProdutoEntrada() {
        return quantidadeProdutoEntrada;
    }

    public void setQuantidadeProdutoEntrada(int quantidadeProdutoEntrada) {
        this.quantidadeProdutoEntrada = quantidadeProdutoEntrada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(int notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
}
