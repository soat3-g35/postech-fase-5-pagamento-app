package br.com.fiap.pos.soat3.pagamento.infrastructure.gateways;

import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;
import br.com.fiap.pos.soat3.pagamento.infrastructure.persistence.PagamentoEntity;
public class PagamentoEntityMapper {
    PagamentoEntity toEntity(Pagamento pagamentoDomainObj) {
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        pagamentoEntity.setPedidoId(pagamentoDomainObj.getPedidoId());
        pagamentoEntity.setClienteId(pagamentoDomainObj.getClienteId());
        pagamentoEntity.setValor(pagamentoDomainObj.getValor());
        pagamentoEntity.setQrCode(pagamentoDomainObj.getQrCode());
        pagamentoEntity.setWebhook(pagamentoDomainObj.getWebhook());
        pagamentoEntity.setStatus(pagamentoDomainObj.getStatus().getStatus());
        pagamentoEntity.setDataDeCriacao(pagamentoDomainObj.getDataDeCriacao().toString());
        return pagamentoEntity;
    }
}
