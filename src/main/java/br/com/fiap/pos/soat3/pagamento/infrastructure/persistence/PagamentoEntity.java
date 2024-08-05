package br.com.fiap.pos.soat3.pagamento.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagamento")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    private Long id;

    @Column(name = "pedido_id")
    private String pedidoId;

    @Column(name = "cliente_id")
    private String clienteId;

    @Column(name = "data_de_criacao")
    private LocalDateTime dataDeCriacao;

    @Column(name = "valor")
    private String valor;

    @Column(name = "status")
    private String status;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "webhook")
    private String webhook;

    public PagamentoEntity() {
    }

    public PagamentoEntity(Long id, String pedidoId, String clienteId, LocalDateTime dataDeCriacao, String valor, String status, String qrCode, String webhook) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.clienteId = clienteId;
        this.dataDeCriacao = dataDeCriacao;
        this.valor = valor;
        this.status = status;
        this.qrCode = qrCode;
        this.webhook = webhook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
