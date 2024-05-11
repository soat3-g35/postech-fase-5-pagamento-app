package br.com.fiap.pos.soat3.pagamento.infrastructure.controllers;

public record PagamentoResponse(Long id, String pedidoId, String qrCode) {
}
