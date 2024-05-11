package br.com.fiap.pos.soat3.pagamento.infrastructure.integration;

import br.com.fiap.pos.soat3.pagamento.application.gateways.EnviaConfirmacaoGateway;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.ConfirmacaoResponse;

public class EnviaConfirmacaoMock implements EnviaConfirmacaoGateway {

    private final MVPCliente mVPCliente;

    public EnviaConfirmacaoMock(MVPCliente mVPCliente) {
        this.mVPCliente = mVPCliente;
    }

    @Override
    public ConfirmacaoResponse enviaConfirmacaoMVP(String pagamentoId) {
        return mVPCliente.verificaPagamentoMock(pagamentoId).getBody();
    }
}
