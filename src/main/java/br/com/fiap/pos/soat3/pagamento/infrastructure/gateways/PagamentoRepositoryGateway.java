package br.com.fiap.pos.soat3.pagamento.infrastructure.gateways;

import br.com.fiap.pos.soat3.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pos.soat3.pagamento.application.gateways.RealizaPagamentoMockGateway;
import br.com.fiap.pos.soat3.pagamento.domain.entity.Pagamento;
import br.com.fiap.pos.soat3.pagamento.domain.entity.StatusPagamento;
import br.com.fiap.pos.soat3.pagamento.infrastructure.integration.MVPResponse;
import br.com.fiap.pos.soat3.pagamento.infrastructure.persistence.PagamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class PagamentoRepositoryGateway implements PagamentoGateway {

    private final Logger log = LoggerFactory.getLogger(PagamentoRepositoryGateway.class);
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoEntityMapper pagamentoEntityMapper;
    private final RealizaPagamentoMockGateway realizaPagamentoMockGateway;

    public PagamentoRepositoryGateway(final PagamentoRepository pagamentoRepository,
                                      final PagamentoEntityMapper pagamentoEntityMapper,
                                      final RealizaPagamentoMockGateway realizaPagamentoMockGateway) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoEntityMapper = pagamentoEntityMapper;
        this.realizaPagamentoMockGateway = realizaPagamentoMockGateway;

    }

    @Override
    public Pagamento realizaPagamento(Pagamento pagamento) {
        
        MVPResponse response = realizaPagamentoMockGateway.realizaPagamentoMVP(pagamento.getPagamentoId());
        pagamento.setQrCode(response.getCode());
        pagamento.setWebhook(response.getUrl());
        pagamento.setStatus(StatusPagamento.AGUARDANDO_PAGAMENTO);
        pagamento.setDataDeCriacao(LocalDateTime.now());

        var pagamentoEntity = pagamentoEntityMapper.toEntity(pagamento);
        pagamento.setPagamentoId(pagamentoRepository.criaPagamento(pagamentoEntity));

        
        log.info(String.format("Lanchonete: Pagamento do pedido  %s gerado", pagamento.getPagamentoId()));
        return pagamento;
    }

    @Override
    public void atualizaPagamento(String pagamentoId, String resultado) {
        pagamentoRepository.atualizaStatus(pagamentoId, resultado);
    }
}
