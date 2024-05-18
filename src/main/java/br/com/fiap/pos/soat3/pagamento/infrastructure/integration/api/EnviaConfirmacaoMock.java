package br.com.fiap.pos.soat3.pagamento.infrastructure.integration.api;

import br.com.fiap.pos.soat3.pagamento.application.gateways.EnviaConfirmacaoGateway;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.ConfirmacaoResponse;

public class EnviaConfirmacaoMock implements EnviaConfirmacaoGateway {

    private final MVPCliente mVPCliente;
    
    public EnviaConfirmacaoMock(MVPCliente mVPCliente) {
        this.mVPCliente = mVPCliente;
    }

    @Override
    public ConfirmacaoResponse enviaConfirmacaoMVP(String pagamentoId, String pedidoID) {
        return mVPCliente.verificaPagamentoMock(pagamentoId).getBody();
    }
}
