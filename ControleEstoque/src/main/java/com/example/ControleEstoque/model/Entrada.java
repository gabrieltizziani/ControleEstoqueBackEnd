package com.example.ControleEstoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private float quantidadeProdutoEntrada;

    @NotNull(message = "O campo tipo não pode ser nulo.")
    @Column(nullable = false)
    private String tipo;  // Pode ser 'Unidade' ou 'Metro'

    @NotNull(message = "O campo tipo não pode ser nulo.")
    @Column(nullable = false)
    private String fornecedor;

    @Column(nullable = false)
    private float valorTotal;

    @Column(nullable = false)
    private int notaFiscal;

}


