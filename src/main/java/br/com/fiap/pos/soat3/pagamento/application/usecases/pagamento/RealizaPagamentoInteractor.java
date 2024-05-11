package br.com.fiap.pos.soat3.pagamento.application.usecases.pagamento;

import br.com.fiap.pos.soat3.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;

public class RealizaPagamentoInteractor {
    private final PagamentoGateway pagamentoGateway;

    public RealizaPagamentoInteractor(PagamentoGateway pagamentoGateway) {
        this.pagamentoGateway = pagamentoGateway;
    }

    public Pagamento realizaPagamento(Pagamento pagamento) {
        return pagamentoGateway.realizaPagamento(pagamento);
    }
}
