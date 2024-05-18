package br.com.fiap.pos.soat3.pagamento.application.gateways;

import br.com.fiap.pos.soat3.pagamento.infrastructure.integration.api.MVPResponse;

public interface RealizaPagamentoMockGateway {

    MVPResponse realizaPagamentoMVP(String pagamentoId);
}
