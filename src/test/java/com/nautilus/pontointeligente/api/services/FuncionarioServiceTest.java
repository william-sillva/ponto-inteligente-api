package com.nautilus.pontointeligente.api.services;

import com.nautilus.pontointeligente.api.entities.Funcionario;
import com.nautilus.pontointeligente.api.repositories.FuncionarioRepository;
import com.nautilus.pontointeligente.api.services.impl.EmpresaServiceImpl;
import com.nautilus.pontointeligente.api.services.impl.FuncionarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @BeforeEach
    public void setUp() throws Exception {
        BDDMockito.given(this.funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
        BDDMockito.given(this.funcionarioRepository.findById(Mockito.anyLong())).willReturn(java.util.Optional.of(new Funcionario()));
        BDDMockito.given(this.funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
        BDDMockito.given(this.funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
    }

    @Test
    public void testPersistirFuncionario() {
        Funcionario funcionario = this.funcionarioService.persistir(new Funcionario());
        Assertions.assertNotNull(funcionario);
    }

    @Test
    public void testBuscarFuncionarioPorId() {
        Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(1L);
        Assertions.assertTrue(funcionario.isPresent());
    }

    @Test
    public void testBuscarFuncionarioPorEmail() {
        Optional<Funcionario> funcionario = this.funcionarioService.buscarPorEmail("email@email.com");
        Assertions.assertTrue(funcionario.isPresent());
    }

    @Test
    public void testBuscarFuncionarioPorCpf() {
        Optional<Funcionario> funcionario = this.funcionarioService.buscarPorCpf("65498765432");
        Assertions.assertTrue(funcionario.isPresent());
    }

    @Configuration
    @Import(FuncionarServiceImpl.class)
    static class Config {}
}
