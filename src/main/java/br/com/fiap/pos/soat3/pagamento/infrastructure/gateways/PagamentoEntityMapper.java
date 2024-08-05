package br.com.fiap.pos.soat3.pagamento.infrastructure.gateways;

import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;
import br.com.fiap.pos.soat3.pagamento.domain.entity.StatusPagamento;
import br.com.fiap.pos.soat3.pagamento.infrastructure.persistence.PagamentoEntity;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class PagamentoEntityMapper {
    PagamentoEntity toEntity(Pagamento pagamentoDomainObj) {
        PagamentoEntity pagamentoEntity = new PagamentoEntity();
        pagamentoEntity.setPedidoId(pagamentoDomainObj.getPedidoId());
        pagamentoEntity.setClienteId(pagamentoDomainObj.getClienteId());
        pagamentoEntity.setValor(pagamentoDomainObj.getValor());
        pagamentoEntity.setQrCode(pagamentoDomainObj.getQrCode());
        pagamentoEntity.setWebhook(pagamentoDomainObj.getWebhook());
        pagamentoEntity.setStatus(pagamentoDomainObj.getStatus().getStatus());
        pagamentoEntity.setDataDeCriacao(pagamentoDomainObj.getDataDeCriacao());
        return pagamentoEntity;
    }

    Pagamento toDomain(Optional<PagamentoEntity> pagamentoEntity) {
        Pagamento pagamentoDomain = new Pagamento();
        PagamentoEntity entity = pagamentoEntity.orElseThrow(EntityNotFoundException::new);
        pagamentoDomain.setPagamentoId(entity.getId());
        pagamentoDomain.setPedidoId(entity.getPedidoId());
        pagamentoDomain.setClienteId(entity.getClienteId());
        pagamentoDomain.setValor(entity.getValor());
        pagamentoDomain.setQrCode(entity.getQrCode());
        pagamentoDomain.setWebhook(entity.getWebhook());
        pagamentoDomain.setStatus(StatusPagamento.valueOf(entity.getStatus()));
        pagamentoDomain.setDataDeCriacao(entity.getDataDeCriacao());
        return pagamentoDomain;
    }
}
