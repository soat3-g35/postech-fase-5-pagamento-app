package br.com.fiap.pos.soat3.pagamento.domain.entity;

public enum StatusPagamento {
    AGUARDANDO_PAGAMENTO("Aguardando Pagamento"),
    RECEBIDO("Recebido"),
    RECUSADO("Recusado");

    private final String status;

    StatusPagamento(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
