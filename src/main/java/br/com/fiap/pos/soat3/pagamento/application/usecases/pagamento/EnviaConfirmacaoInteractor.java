package br.com.fiap.pos.soat3.pagamento.application.usecases.pagamento;

import br.com.fiap.pos.soat3.pagamento.application.gateways.EnviaConfirmacaoGateway;
import br.com.fiap.pos.soat3.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.ConfirmacaoResponse;
import br.com.fiap.pos.soat3.pagamento.infrastructure.integration.messaging.pagamentoconfirmado.PagamentoConfirmadoPublisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

public class EnviaConfirmacaoInteractor {

    private final Logger log = LoggerFactory.getLogger(EnviaConfirmacaoInteractor.class);

    private final EnviaConfirmacaoGateway enviaConfirmacaoGateway;
    private final PagamentoGateway pagamentoGateway;

    private final PagamentoConfirmadoPublisher pagamentoConfirmadoPublisher;


    public EnviaConfirmacaoInteractor(EnviaConfirmacaoGateway enviaConfirmacaoGateway, PagamentoGateway pagamentoGateway,
                                      PagamentoConfirmadoPublisher pagamentoConfirmadoPublisher) {
        this.enviaConfirmacaoGateway = enviaConfirmacaoGateway;
        this.pagamentoGateway = pagamentoGateway;
        this.pagamentoConfirmadoPublisher = pagamentoConfirmadoPublisher;
    }

    @Transactional
    public ConfirmacaoResponse enviaConfirmacao(Long pagamentoId, String pedidoId) {
        
        ConfirmacaoResponse response = enviaConfirmacaoGateway.enviaConfirmacaoMVP(pagamentoId,pedidoId);
        pagamentoGateway.atualizaPagamento(pagamentoId, response.getResultado());
        log.info("SAGA 6: Publica pagamento confirmado, pagamentoId {}", pagamentoId);
        pagamentoConfirmadoPublisher.publishMessage(pagamentoGateway.findById(pagamentoId));
        log.info("Envio de confirmação de pagamento, resultado:  {}", response.getResultado());
        return response;
    }
}
