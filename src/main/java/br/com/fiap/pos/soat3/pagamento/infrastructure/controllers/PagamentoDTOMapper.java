package br.com.fiap.pos.soat3.pagamento.infrastructure.controllers;

import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;

public class PagamentoDTOMapper {
    public Pagamento toPagamento(PagamentoRequest request) {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(request.valor());
        pagamento.setPedidoId(request.pedidoId().toString());
        pagamento.setClienteId(request.clienteId().toString());
        return pagamento;
    }

    RealizaPagamentoResponse toResponse(Pagamento pagamento) {
        return new RealizaPagamentoResponse(
                pagamento.getPagamentoId(),
                pagamento.getPedidoId(),
                pagamento.getValor(),
                pagamento.getStatus(),
                pagamento.getDataDeCriacao(),
                pagamento.getQrCode());

    }
}
