package br.com.fiap.pos.soat3.pagamento.infrastructure.integration.api;

import br.com.fiap.pos.soat3.pagamento.application.gateways.RealizaPagamentoMockGateway;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RealizaPagamentoMock implements RealizaPagamentoMockGateway {

    @Override
    public MVPResponse realizaPagamentoMVP(String pagamentoId) {
        return new MVPResponse(String.valueOf(Math.abs(new Random().nextInt())),
                "http://webhook-mock:9999/mock/".concat(String.valueOf(pagamentoId)));
    }
}
