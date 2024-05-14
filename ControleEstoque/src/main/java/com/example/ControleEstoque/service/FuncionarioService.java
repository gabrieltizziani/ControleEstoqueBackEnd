package com.example.ControleEstoque.service;

import com.example.ControleEstoque.model.Funcionario;
import com.example.ControleEstoque.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FuncionarioService implements CrudFuncionarioService<Funcionario>{
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Override
    public List<Funcionario> listarFuncionario(){
        return funcionarioRepository.findAll();
    }

    @Override
    public Funcionario criarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }


    @Override
    public Funcionario editarFuncionario(Funcionario funcionario, Long idFuncionario){
        if (funcionarioRepository.existsById(idFuncionario)) {
            funcionario.setIdFuncionario(idFuncionario);
            return funcionarioRepository.save(funcionario);
        } return null;
    }

    @Override
    public boolean excluirFuncionario(Long idFuncionario){
        if(funcionarioRepository.existsById(idFuncionario)) {
            funcionarioRepository.deleteById(idFuncionario);
            return true;
        } return false;
    }
}