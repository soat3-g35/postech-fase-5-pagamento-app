package br.com.fiap.pos.soat3.pagamento.infrastructure.integration.api;

import br.com.fiap.pos.soat3.pagamento.application.gateways.RealizaPagamentoMockGateway;
import org.springframework.stereotype.Component;

@Component
public class RealizaPagamentoMock implements RealizaPagamentoMockGateway {

    private final MVPCliente mVPCliente;

    public RealizaPagamentoMock(MVPCliente mVPCliente) {
        this.mVPCliente = mVPCliente;
    }

    @Override
    public MVPResponse realizaPagamentoMVP(String pagamentoId) {
        return mVPCliente.realizaPagamentoMock(pagamentoId);
    }
}
