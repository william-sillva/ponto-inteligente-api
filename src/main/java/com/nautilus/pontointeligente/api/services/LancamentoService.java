package com.nautilus.pontointeligente.api.services;

import com.nautilus.pontointeligente.api.entities.Lancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface LancamentoService {

    Page<Lancamento> buscarPorFuncionarioId(Long idFuncionario, PageRequest pageRequest);

    Optional<Lancamento> buscarPorId(Long id);

    Lancamento persistir(Lancamento lancamento);

    void remover(Long id);
}
