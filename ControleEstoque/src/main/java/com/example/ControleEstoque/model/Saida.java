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
    private float quantidadeProduto;

    @NotNull(message = "O campo tipo não pode ser nulo.")
    @Column(nullable = false)
    private String tipo;  // Pode ser 'Unidade' ou 'Metro'

    @Column(nullable = false)
    private float valorTotal;

}
