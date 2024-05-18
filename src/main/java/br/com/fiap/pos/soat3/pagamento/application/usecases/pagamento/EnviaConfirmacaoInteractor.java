package br.com.fiap.pos.soat3.pagamento.application.usecases.pagamento;

import br.com.fiap.pos.soat3.pagamento.application.gateways.EnviaConfirmacaoGateway;
import br.com.fiap.pos.soat3.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pos.soat3.pagamento.infrastructure.integration.messaging.UpdatePagamentoStatusPublisher;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.ConfirmacaoResponse;

public class EnviaConfirmacaoInteractor {

    private final EnviaConfirmacaoGateway enviaConfirmacaoGateway;
    private final PagamentoGateway pagamentoGateway;

    private final UpdatePagamentoStatusPublisher updatePagamentoStatusPublisher;


    public EnviaConfirmacaoInteractor(EnviaConfirmacaoGateway enviaConfirmacaoGateway, PagamentoGateway pagamentoGateway,
                                      UpdatePagamentoStatusPublisher updatePagamentoStatusPublisher) {
        this.enviaConfirmacaoGateway = enviaConfirmacaoGateway;
        this.pagamentoGateway = pagamentoGateway;
        this.updatePagamentoStatusPublisher = updatePagamentoStatusPublisher;
    }
    public void enviaConfirmacao(String pagamentoId, String pedidoId) {
        
        ConfirmacaoResponse response = enviaConfirmacaoGateway.enviaConfirmacaoMVP(pagamentoId,pedidoId);
        updatePagamentoStatusPublisher.publishMessage(pedidoId, response.getResultado());
        pagamentoGateway.atualizaPagamento(pagamentoId, response.getResultado());
    }
}
