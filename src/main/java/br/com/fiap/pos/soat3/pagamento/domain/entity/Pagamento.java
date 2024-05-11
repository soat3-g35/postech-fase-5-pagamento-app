package br.com.fiap.pos.soat3.pagamento.domain.entity;

import java.time.LocalDateTime;

public class Pagamento {

    private String pagamentoId;

    private String pedidoId;

    private String clienteId;

    private LocalDateTime dataDeCriacao;

    private String valor;

    private StatusPagamento status;

    private String qrCode;

    private String webhook;

    public Pagamento() {

    }

    public Pagamento(String pagamentoId, String pedidoId, String clienteId, LocalDateTime dataDeCriacao, String valor, StatusPagamento status, String qrCode, String webhook) {
        this.pagamentoId = pagamentoId;
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.dataDeCriacao = dataDeCriacao;
        this.valor = valor;
        this.status = status;
        this.qrCode = qrCode;
        this.webhook = webhook;
    }

    public String getPagamentoId() {
        return pagamentoId;
    }

    public void setPagamentoId(String pagamentoId) {
        this.pagamentoId = pagamentoId;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }
}
