package br.com.fiap.pos.soat3.pagamento.infrastructure.integration.messaging;

public class PagamentoStatusMessage {

    private String pedidoId;
    private String status;

    public PagamentoStatusMessage(String pedidoId, String status) {
        this.pedidoId = pedidoId;
        this.status = status;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(String pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
