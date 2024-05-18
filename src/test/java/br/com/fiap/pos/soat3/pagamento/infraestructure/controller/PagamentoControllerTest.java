package br.com.fiap.pos.soat3.pagamento.infraestructure.controller;

import br.com.fiap.pos.soat3.pagamento.application.usecases.pagamento.EnviaConfirmacaoInteractor;
import br.com.fiap.pos.soat3.pagamento.application.usecases.pagamento.RealizaPagamentoInteractor;
import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;
import br.com.fiap.pos.soat3.pagamento.domain.entity.StatusPagamento;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.ConfirmacaoResponse;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.PagamentoController;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.PagamentoDTOMapper;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.PagamentoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class PagamentoControllerTest {
    
    private MockMvc mvc;
    @Mock 
    private RealizaPagamentoInteractor realizaPagamentoInteractor;

    @Mock
    private EnviaConfirmacaoInteractor enviaConfirmacaoInteractor;
    
    @Mock 
    private PagamentoDTOMapper pagamentoDTOMapper;
    
    @InjectMocks
    private PagamentoController pagamentoController;

    private JacksonTester<PagamentoRequest> jsonPagamentoRequest;
    private JacksonTester<Pagamento> jsonPagamento;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(pagamentoController)
//                .setControllerAdvice(new SuperHeroExceptionHandler())
//                .addFilters(new SuperHeroFilter())
                .build();
    }
    
    @Test
    void givenPagamentoRequestWhenCallShouldReturnSuccess() throws Exception {
        // given
        Pagamento pagamento = new Pagamento("1", "2", "1", LocalDateTime.now(), "1000", StatusPagamento.RECEBIDO, "123", "" );
        given(realizaPagamentoInteractor.realizaPagamento(any()))
                .willReturn(pagamento);

        // when
        MockHttpServletResponse response = mvc.perform(
                post("/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPagamentoRequest.write(new PagamentoRequest(1L, 2L, "1000")).getJson()
                )).andReturn().getResponse();


        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void givenPagamentoConfirmacaoWhenCallShouldReturnSuccess() throws Exception {

        UUID pagamentoId = UUID.randomUUID();
        Long pedidoId = 1L;
        // given
        given(enviaConfirmacaoInteractor.enviaConfirmacao(pagamentoId.toString(), pedidoId.toString()))
                .willReturn(new ConfirmacaoResponse("RECEBIDO"));

        // when
        MockHttpServletResponse response = mvc.perform(
                patch(String.format("/pagamento/%s/pedido/%s/envia-confirmacao", pagamentoId, pedidoId))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();


        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}
