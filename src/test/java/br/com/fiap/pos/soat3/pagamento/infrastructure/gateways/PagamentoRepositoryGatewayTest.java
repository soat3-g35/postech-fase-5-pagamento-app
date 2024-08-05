package br.com.fiap.pos.soat3.pagamento.infrastructure.gateways;


import br.com.fiap.pos.soat3.pagamento.application.gateways.RealizaPagamentoMockGateway;
import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;
import br.com.fiap.pos.soat3.pagamento.domain.entity.StatusPagamento;
import br.com.fiap.pos.soat3.pagamento.infrastructure.integration.api.MVPResponse;
import br.com.fiap.pos.soat3.pagamento.infrastructure.persistence.PagamentoEntity;
import br.com.fiap.pos.soat3.pagamento.infrastructure.persistence.PagamentoRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PagamentoRepositoryGatewayTest {

    @Mock
    private Logger log = LoggerFactory.getLogger(PagamentoRepositoryGateway.class);

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private PagamentoEntityMapper pagamentoEntityMapper;

    @Mock
    private RealizaPagamentoMockGateway realizaPagamentoMockGateway;

//    @Mock
//    private UpdatePagamentoStatusPublisher updatePagamentoStatusPublisher;

    @InjectMocks
    PagamentoRepositoryGateway gateway;

//    @Test
//    void atualizaPagamento_shouldDoWithSuccess() {
//        PagamentoEntity entity = new PagamentoEntity();
//
//        when(pagamentoRepository.atualizaStatus("123", "Recusado")).thenReturn(
//                entity
//        );
//
//        gateway.atualizaPagamento("123", "Recusado");
//
//        verify(pagamentoRepository).atualizaStatus("123", "Recusado");
//    }

//    @Test
//    void realizaPagamento_shouldDoWithSuccess() {
//        Pagamento model = new Pagamento(
//                "123",
//                "321",
//                "111",
//                LocalDateTime.now(),
//                "20",
//                StatusPagamento.AGUARDANDO_PAGAMENTO,
//                "qrCode",
//                "webhook"
//        );
//
//        PagamentoEntity entity = new PagamentoEntity();
//        when(pagamentoEntityMapper.toEntity(model)).thenReturn(entity);
////        doNothing().when(updatePagamentoStatusPublisher).publishMessage(any(), any());
//
//        when(realizaPagamentoMockGateway.realizaPagamentoMVP(model.getPagamentoId())).thenReturn(
//                new MVPResponse("qrCode", "www")
//        );
//
//        Pagamento current = gateway.realizaPagamento(model);
//
//        assertEquals("", model, current);
////        verify(updatePagamentoStatusPublisher).publishMessage("321", "Aguardando Pagamento");
//    }
}
