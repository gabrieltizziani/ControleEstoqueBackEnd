package com.example.ControleEstoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduto;
    @NotNull(message = "O campo nome não pode ser nulo.")
    @Column(nullable = false, unique = true)
    private String nomeProduto;
    @NotNull(message = "O campo preço não pode ser nulo ")
    @Column(nullable = false)
    private float precoProduto;

    public Produto() {
    }

    public Produto(long idProduto, String nomeProduto, float precoProduto) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.precoProduto = precoProduto;
    }



    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public float getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(float precoProduto) {
        this.precoProduto = precoProduto;
    }

    public void setQuantidadeReal(float i) {

    }
}