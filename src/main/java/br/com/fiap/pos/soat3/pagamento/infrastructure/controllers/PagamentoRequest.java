package br.com.fiap.pos.soat3.pagamento.infrastructure.controllers;


public record PagamentoRequest(Long clienteId, Long pedidoId, String valor) {
}
