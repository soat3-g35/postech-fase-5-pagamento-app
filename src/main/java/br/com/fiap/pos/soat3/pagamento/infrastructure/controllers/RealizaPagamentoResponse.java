package br.com.fiap.pos.soat3.pagamento.infrastructure.controllers;

import br.com.fiap.pos.soat3.pagamento.domain.entity.StatusPagamento;

import java.time.LocalDateTime;

public record RealizaPagamentoResponse(
        String pagamentoId,
        String pedidoId,
        String valor,
        StatusPagamento status,
        LocalDateTime dataDeCriacao,
        String qrCode
) {
}
