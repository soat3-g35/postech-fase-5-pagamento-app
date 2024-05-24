package br.com.fiap.pos.soat3.pagamento.bdd;

import br.com.fiap.pos.soat3.pagamento.infrastructure.config.persistence.DynamoDBConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;

import java.io.File;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 10001)
//@AutoConfigureWireMock(port = 10001, stubs = "classpath:/stubs/*/*")
public class CucumberBootstrap {

    @Autowired
    public ObjectMapper mapper;
    @Autowired
    public DynamoDBConfig amazonDynamoDBConfig;

    @Container
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("local-autoservico.yaml"))
                    .withLocalCompose(true)
//                    .withOptions("--compatibility")
                    .withExposedService("localstack", 4566,
                            Wait.forListeningPort()
                    );

    public void createStack() {
        environment.start();
    }

//    public static void stopContainer(){
//        COMPOSE_CONTAINER.stop();
//    }
}
