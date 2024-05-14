package com.example.ControleEstoque.repository;

import com.example.ControleEstoque.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository <Funcionario, Long> {
    Optional<Funcionario> findByNomeFuncionario(String nomeFuncionario);
}
