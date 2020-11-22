package com.nautilus.pontointeligente.api.services;

import com.nautilus.pontointeligente.api.entities.Funcionario;

import java.util.Optional;

public interface FuncionarioService {

    Funcionario persistir(Funcionario funcionario);

    Optional<Funcionario> buscarPorCpf(String cpf);

    Optional<Funcionario> buscarPorEmail(String email);

    Optional<Funcionario> buscarPorId(Long id);
}
