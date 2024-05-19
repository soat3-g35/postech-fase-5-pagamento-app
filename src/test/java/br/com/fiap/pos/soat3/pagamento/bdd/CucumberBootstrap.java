package br.com.fiap.pos.soat3.pagamento.bdd;

import br.com.fiap.pos.soat3.pagamento.infrastructure.config.persistence.DynamoDBConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;

import java.io.File;
import java.time.Duration;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 10001)
//@AutoConfigureWireMock(port = 10001, stubs = "classpath:/stubs/*/*")
public class CucumberBootstrap {

    @Autowired
    public ObjectMapper mapper;
    @Autowired
    public DynamoDBConfig amazonDynamoDBConfig;

//    static final DockerComposeContainer COMPOSE_CONTAINER;

//    static {
//        COMPOSE_CONTAINER = new DockerComposeContainer(new File("local-autoservico.yaml"));
//        COMPOSE_CONTAINER/*.withLocalCompose(true).withOptions("--compatibility")*/
//                .withExposedService("localstack_1", 4566,
//                        Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30))
//                );
//        COMPOSE_CONTAINER.start();
//        Runtime.getRuntime().addShutdownHook(new Thread(() -> stopContainer()));
//    }

    @Container
    public static DockerComposeContainer environment =
            new DockerComposeContainer(new File("local-autoservico.yaml")).withLocalCompose(true)
                    .withOptions("--compatibility")
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
