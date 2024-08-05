package br.com.fiap.pos.soat3.pagamento.infrastructure.gateways;

import br.com.fiap.pos.soat3.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pos.soat3.pagamento.application.gateways.RealizaPagamentoMockGateway;
import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;
import br.com.fiap.pos.soat3.pagamento.domain.entity.StatusPagamento;
import br.com.fiap.pos.soat3.pagamento.infrastructure.integration.api.MVPResponse;
import br.com.fiap.pos.soat3.pagamento.infrastructure.integration.messaging.pagamentopendente.PagamentoPendentePublisher;
import br.com.fiap.pos.soat3.pagamento.infrastructure.persistence.PagamentoEntity;
import br.com.fiap.pos.soat3.pagamento.infrastructure.persistence.PagamentoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class PagamentoRepositoryGateway implements PagamentoGateway {

    private final Logger log = LoggerFactory.getLogger(PagamentoRepositoryGateway.class);
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoEntityMapper pagamentoEntityMapper;
    private final RealizaPagamentoMockGateway realizaPagamentoMockGateway;
    private final PagamentoPendentePublisher pagamentoPendentePublisher;

    public PagamentoRepositoryGateway(final PagamentoRepository pagamentoRepository,
                                      final PagamentoEntityMapper pagamentoEntityMapper,
                                      final RealizaPagamentoMockGateway realizaPagamentoMockGateway,
                                      final PagamentoPendentePublisher pagamentoPendentePublisher) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoEntityMapper = pagamentoEntityMapper;
        this.realizaPagamentoMockGateway = realizaPagamentoMockGateway;
        this.pagamentoPendentePublisher = pagamentoPendentePublisher;
    }


    @Transactional
    @Override
    public Pagamento realizaPagamento(Pagamento pagamento) {
        
        MVPResponse response = realizaPagamentoMockGateway.realizaPagamentoMVP(UUID.randomUUID().toString());
        pagamento.setQrCode(response.getCode());
        pagamento.setWebhook(response.getUrl());
        pagamento.setStatus(StatusPagamento.AGUARDANDO_PAGAMENTO);
        pagamento.setDataDeCriacao(LocalDateTime.now());

        var pagamentoEntity = pagamentoEntityMapper.toEntity(pagamento);
        pagamento.setPagamentoId(pagamentoRepository.save(pagamentoEntity).getId());
        log.info("SAGA 4: Salva pagamento pendente, pagamentoId {}", pagamento.getPagamentoId());

        log.info("SAGA 5: Publica pagamento pendente, pagamentoId {}", pagamento.getPagamentoId());
        pagamentoPendentePublisher.publishMessage(pagamento);
        
        log.info(String.format("Lanchonete: Pagamento do pedido  %s gerado",
                pagamento.getPagamentoId()));
        return pagamento;
    }

    @Override
    public Pagamento findById(Long pagamentoId) {
        return pagamentoEntityMapper.toDomain(pagamentoRepository.findById(pagamentoId));
    }

    @Override
    public void atualizaPagamento(Long pagamentoId, String resultado) {
        Optional<PagamentoEntity> pagamentoEntityFound = pagamentoRepository.findById(pagamentoId);
        PagamentoEntity entity  = pagamentoEntityFound.orElseThrow(EntityNotFoundException::new);
        entity.setStatus(resultado);
        pagamentoRepository.save(entity);
    }
}
