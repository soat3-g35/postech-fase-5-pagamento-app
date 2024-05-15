package br.com.fiap.pos.soat3.pagamento.application.gateways;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TesteDoSonarTest {

    @Autowired
    TesteDoSonar teste;
    @Test
    void contextLoads() {
        var inteiro = teste.retornaInteiro();
        Assertions.assertEquals(inteiro,1);

        var inteiro2 = teste.retornaSegundoInteiro();
        Assertions.assertEquals(inteiro2,2);
    }
}
