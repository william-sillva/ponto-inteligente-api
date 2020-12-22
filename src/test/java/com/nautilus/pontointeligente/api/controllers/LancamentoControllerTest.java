package com.nautilus.pontointeligente.api.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nautilus.pontointeligente.api.dtos.LancamentoDto;
import com.nautilus.pontointeligente.api.entities.Funcionario;
import com.nautilus.pontointeligente.api.entities.Lancamento;
import com.nautilus.pontointeligente.api.enums.TipoEnum;
import com.nautilus.pontointeligente.api.services.FuncionarioService;
import com.nautilus.pontointeligente.api.services.LancamentoService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LancamentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LancamentoService lancamentoService;

    @MockBean
    private FuncionarioService funcionarioService;

    private static final String URL_BASE = "/api/lancamentos/";

    private static final Long ID_FUNCIONARIO = 1L;

    private static final Long ID_LANCAMENTO = 1L;

    private static final String TIPO = TipoEnum.INICIO_TRABALHO.name();

    private static final Date DATA = new Date();

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testCadastrarLancamento() throws Exception {
        Lancamento lancamento = obterDadosLancamento();
        BDDMockito.given(this.funcionarioService.buscarPorId(Mockito.anyLong())).willReturn(Optional.of(new Funcionario()));
        BDDMockito.given(this.lancamentoService.persistir(Mockito.any(Lancamento.class))).willReturn(lancamento);

        mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                .content(this.obterJsonRequisicaoPost())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id").value(ID_LANCAMENTO))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.tipo").value(TIPO))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data").value(this.dateFormat.format(DATA)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.funcionarioId").value(ID_FUNCIONARIO))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.descricao").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.localizacao").isEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").isEmpty());
    }

    @Test
    public void testCadastrarLancamentoFuncionarioIdInvalido() throws Exception {
        BDDMockito.given(this.funcionarioService.buscarPorId(Mockito.anyLong())).willReturn(Optional.empty());

        mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
                .content(this.obterJsonRequisicaoPost())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errors").value("Funcionário não encontrado. ID inexistente."))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());
    }

    @Test
    public void testRemoverLancamento() throws Exception {
        BDDMockito.given(this.lancamentoService.buscarPorId(Mockito.anyLong())).willReturn(Optional.of(new Lancamento()));

        mvc.perform(MockMvcRequestBuilders.delete(URL_BASE + ID_LANCAMENTO)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String obterJsonRequisicaoPost() throws JsonProcessingException {
        LancamentoDto lancamentoDto = new LancamentoDto();
        lancamentoDto.setId(null);
        lancamentoDto.setData(this.dateFormat.format(DATA));
        lancamentoDto.setTipo(TIPO);
        lancamentoDto.setFuncionarioId(ID_FUNCIONARIO);
        lancamentoDto.setDescricao(null);
        lancamentoDto.setLocalizacao(null);
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(lancamentoDto);
    }

    private Lancamento obterDadosLancamento() {
        Lancamento lancamento = new Lancamento();
        lancamento.setId(ID_LANCAMENTO);
        lancamento.setData(DATA);
        lancamento.setTipo(TipoEnum.valueOf(TIPO));
        lancamento.setFuncionario(new Funcionario());
        lancamento.getFuncionario().setId(ID_FUNCIONARIO);

        return lancamento;
    }
}