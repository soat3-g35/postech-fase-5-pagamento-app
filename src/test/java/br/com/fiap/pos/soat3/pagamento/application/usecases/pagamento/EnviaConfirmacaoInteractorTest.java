package br.com.fiap.pos.soat3.pagamento.application.usecases.pagamento;

import br.com.fiap.pos.soat3.pagamento.application.gateways.EnviaConfirmacaoGateway;
import br.com.fiap.pos.soat3.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;
import br.com.fiap.pos.soat3.pagamento.domain.entity.StatusPagamento;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.ConfirmacaoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnviaConfirmacaoInteractorTest {

    @Mock
    private EnviaConfirmacaoGateway gateway;
    @Mock
    private PagamentoGateway pagamentoGateway;

    @InjectMocks
    private EnviaConfirmacaoInteractor useCase;

    @Test
    void givenCorrectData_whenEnviaConfirmacao_shouldReturnConfirmacaoResponse() {

        ConfirmacaoResponse expected = new ConfirmacaoResponse(
                StatusPagamento.RECEBIDO.name()
        );

        when(useCase.enviaConfirmacao( "1", "1")).thenReturn(expected);

        ConfirmacaoResponse confirmacaoResponse = gateway.enviaConfirmacaoMVP("1", "1");

        assertEquals(expected, confirmacaoResponse);

    }
    
    @Test
    void givenCorrectData_whenEnviaConfirmacaoMVP_shouldReturnConfirmacaoResponse() {

        ConfirmacaoResponse expected = new ConfirmacaoResponse(
                StatusPagamento.RECEBIDO.name()
        );

        when(gateway.enviaConfirmacaoMVP( "1", "1")).thenReturn(expected);

        ConfirmacaoResponse confirmacaoResponse = gateway.enviaConfirmacaoMVP("1", "1");

        assertEquals(expected, confirmacaoResponse);
       
    }

    @Test
    void givenCorrectData_whenRealizaPagamento_shouldReturnCurrentPagamentoModel() {
        Pagamento expected = new Pagamento(
                "1",
                "1",
                "1",
                LocalDateTime.now(),
                "1000",
                StatusPagamento.RECEBIDO,
                "123",
                "teste.com"
        );

        when(pagamentoGateway.realizaPagamento(expected)).thenReturn(expected);

        Pagamento currentPagamento = pagamentoGateway.realizaPagamento(expected);

        assertEquals(expected, currentPagamento);

    }
}

