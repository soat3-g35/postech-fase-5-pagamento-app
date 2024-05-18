package br.com.fiap.pos.soat3.pagamento.infraestructure.controller;

import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.PagamentoDTOMapper;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.PagamentoRequest;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.RealizaPagamentoResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PagamentoDTOMapperTest {

    private PagamentoDTOMapper mapper = new PagamentoDTOMapper();
    
    @Test
    void givenRequest_whenMapper_shouldReturnModel() {
        PagamentoRequest response = new PagamentoRequest(
                1L, 1L, "100"
        );

        Pagamento expected = new Pagamento(
                "1", "1", "100", null, null, null, null, null
        );

        Pagamento result = mapper.toPagamento(response);

        assertEquals(expected.getPedidoId(), result.getPedidoId());
    }

    @Test
    void givenModel_whenMapper_shouldReturnResponse() {
        RealizaPagamentoResponse expected = new RealizaPagamentoResponse(
                "1", "1", "100", null, null, null
        );

        Pagamento model = new Pagamento(
                "1", "1", "1", null, "100", null, null, null
        );

        RealizaPagamentoResponse result = mapper.toResponse(model);

        assertEquals(expected, result);
    }
}
