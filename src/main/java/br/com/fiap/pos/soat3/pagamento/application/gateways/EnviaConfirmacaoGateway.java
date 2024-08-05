package br.com.fiap.pos.soat3.pagamento.application.gateways;

import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.ConfirmacaoResponse;

public interface EnviaConfirmacaoGateway {
    ConfirmacaoResponse enviaConfirmacaoMVP(Long pagamentoId, String pedidoId);
}
