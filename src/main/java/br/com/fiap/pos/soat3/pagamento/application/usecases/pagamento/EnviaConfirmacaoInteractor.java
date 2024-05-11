package br.com.fiap.pos.soat3.pagamento.application.usecases.pagamento;

import br.com.fiap.pos.soat3.pagamento.application.gateways.EnviaConfirmacaoGateway;
import br.com.fiap.pos.soat3.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.ConfirmacaoResponse;

public class EnviaConfirmacaoInteractor {

    private final EnviaConfirmacaoGateway enviaConfirmacaoGateway;
    private final PagamentoGateway pagamentoGateway;

    public EnviaConfirmacaoInteractor(EnviaConfirmacaoGateway enviaConfirmacaoGateway, PagamentoGateway pagamentoGateway) {
        this.enviaConfirmacaoGateway = enviaConfirmacaoGateway;
        this.pagamentoGateway = pagamentoGateway;
    }
    public void enviaConfirmacao(String pagamentoId) {
        ConfirmacaoResponse response = enviaConfirmacaoGateway.enviaConfirmacaoMVP(pagamentoId);
        pagamentoGateway.atualizaPagamento(pagamentoId, response.getResultado());
    }
}
