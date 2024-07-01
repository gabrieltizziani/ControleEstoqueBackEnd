package com.example.ControleEstoque.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;
    @NotNull(message = "O campo nome não pode ser nulo.")
    @Column(nullable = false, unique = true)
    private String nomeFuncionario;

    @NotNull(message = "Campo número não pode ser nulo.") //O CPF não pode ser nulo
    @Column(nullable = false)
    // O banco de dados não deve permitir valores nulos na coluna correspondente, não tera cpf duplicado
    @Pattern(regexp = "[0-9]+", message = "Deve conter apenas números")
    private String numeroFuncionario;

    @NotNull(message = "O campo nome não pode ser nulo.")
    @Column(nullable = false)
    private String funcaoFuncionario;



    public Funcionario() {
    }

    public Funcionario(String nomeFuncionario, String numeroFuncionario, String funcaoFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
        this.numeroFuncionario = numeroFuncionario;
        this.funcaoFuncionario = funcaoFuncionario;

    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public void setNumeroFuncionario(String numeroFuncionario) {
        this.numeroFuncionario = numeroFuncionario;
    }

    public String getFuncaoFuncionario() {
        return funcaoFuncionario;
    }

    public void setFuncaoFuncionario(String funcaoFuncionario) {
        this.funcaoFuncionario = funcaoFuncionario;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }


}