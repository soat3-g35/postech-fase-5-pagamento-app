package br.com.fiap.pos.soat3.pagamento.infrastructure.integration.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnviaConfirmacaoMockTest {
    @Autowired
    EnviaConfirmacaoMock enviaConfirmacaoMock;

    @Test
    void contextLoads() {
        var result = enviaConfirmacaoMock.enviaConfirmacaoMVP(1L, "pedidoId");
        Assertions.assertEquals ("RECEBIDO", result.getResultado());
    }
}
