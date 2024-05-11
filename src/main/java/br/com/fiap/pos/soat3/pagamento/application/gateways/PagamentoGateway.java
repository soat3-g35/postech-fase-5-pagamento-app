package br.com.fiap.pos.soat3.pagamento.application.gateways;

import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;

public interface PagamentoGateway {
    Pagamento realizaPagamento(Pagamento pagamento);

    void atualizaPagamento(String pagamentoId, String resultado);
}
