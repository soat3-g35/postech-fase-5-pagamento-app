package br.com.fiap.pos.soat3.pagamento.infrastructure.integration.api;

import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.ConfirmacaoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface MVPCliente {

    MVPResponse realizaPagamentoMock(String pagamentoId);

    @GetMapping("/mock/{pagamentoId}")
    ResponseEntity<ConfirmacaoResponse> verificaPagamentoMock(@PathVariable("pagamentoId") String pagamentoId);
}
