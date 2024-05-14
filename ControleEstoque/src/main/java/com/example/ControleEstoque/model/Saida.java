package com.example.ControleEstoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Saida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSaida;

    @NotNull(message = "O campo data não pode ser nulo")
    @Column(nullable = false)
    private LocalDate dataSaida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nomeFuncionario", referencedColumnName = "nomeFuncionario")
    private Funcionario funcionario;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nomeProduto", referencedColumnName = "nomeProduto")
    private Produto produto;

    @NotNull(message = "O campo quantidade não pode ser nulo.")
    @Column(nullable = false)
    private int quantidadeProduto;

    @NotNull(message = "O campo tipo não pode ser nulo.")
    @Column(nullable = false)
    private String tipo;  // Pode ser 'Unidade' ou 'Metro'

    @Column(nullable = false)
    private float valorTotal;

    public Saida() {
    }


    public Saida(Long idSaida, LocalDate dataSaida, Funcionario funcionario, Produto produto, int quantidadeProduto, String tipo, float valorTotal) {
        this.idSaida = idSaida;
        this.dataSaida = dataSaida;
        this.funcionario = funcionario;
        this.produto = produto;
        this.quantidadeProduto = quantidadeProduto;
        this.tipo = tipo;
        this.valorTotal = valorTotal;
    }

    public Long getIdSaida() {
        return idSaida;
    }

    public void setIdSaida(Long idSaida) {
        this.idSaida = idSaida;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }


    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
}
