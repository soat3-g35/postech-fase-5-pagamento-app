package br.com.fiap.pos.soat3.pagamento.infrastructure.config;

import br.com.fiap.pos.soat3.pagamento.application.gateways.EnviaConfirmacaoGateway;
import br.com.fiap.pos.soat3.pagamento.application.gateways.PagamentoGateway;
import br.com.fiap.pos.soat3.pagamento.application.gateways.RealizaPagamentoMockGateway;
import br.com.fiap.pos.soat3.pagamento.application.usecases.pagamento.EnviaConfirmacaoInteractor;
import br.com.fiap.pos.soat3.pagamento.application.usecases.pagamento.RealizaPagamentoInteractor;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.PagamentoDTOMapper;
import br.com.fiap.pos.soat3.pagamento.infrastructure.gateways.PagamentoEntityMapper;
import br.com.fiap.pos.soat3.pagamento.infrastructure.gateways.PagamentoRepositoryGateway;
import br.com.fiap.pos.soat3.pagamento.infrastructure.integration.messaging.pagamentoconfirmado.PagamentoConfirmadoPublisher;
import br.com.fiap.pos.soat3.pagamento.infrastructure.integration.messaging.pagamentopendente.PagamentoPendentePublisher;
import br.com.fiap.pos.soat3.pagamento.infrastructure.persistence.PagamentoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PagamentoBeanConfig {

    @Bean
    PagamentoEntityMapper pagamentoEntityMapper() {
        return new PagamentoEntityMapper();
    }

    @Bean
    PagamentoRepositoryGateway pagamentoRepositoryGateway(PagamentoRepository pagamentoRepository,
                                                          PagamentoEntityMapper pagamentoEntityMapper,
                                                          RealizaPagamentoMockGateway realizaPagamentoMockGateway,
                                                          PagamentoPendentePublisher pagamentoPendentePublisher) {
        return new PagamentoRepositoryGateway(pagamentoRepository, pagamentoEntityMapper, realizaPagamentoMockGateway, pagamentoPendentePublisher);
    }

    @Bean
    RealizaPagamentoInteractor realizaPagamentoUseCase(PagamentoGateway pagamentoGateway) {
        return new RealizaPagamentoInteractor(pagamentoGateway);
    }

    @Bean
    EnviaConfirmacaoInteractor enviaConfirmacaoInteractor(EnviaConfirmacaoGateway enviaConfirmacaoGateway,
                                                          PagamentoGateway pagamentoGateway, PagamentoConfirmadoPublisher pagamentoConfirmadoPublisher) {
        return new EnviaConfirmacaoInteractor(enviaConfirmacaoGateway, pagamentoGateway, pagamentoConfirmadoPublisher);
    }

    @Bean
    PagamentoDTOMapper pagamentoDTOMapper() {
        return new PagamentoDTOMapper();
    }

}
