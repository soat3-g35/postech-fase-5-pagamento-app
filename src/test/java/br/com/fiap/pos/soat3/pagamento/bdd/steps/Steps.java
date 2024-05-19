package br.com.fiap.pos.soat3.pagamento.bdd.steps;

import br.com.fiap.pos.soat3.pagamento.bdd.CucumberBootstrap;
import br.com.fiap.pos.soat3.pagamento.infrastructure.controllers.PagamentoRequest;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@CucumberContextConfiguration
public class Steps extends CucumberBootstrap {

    @LocalServerPort
    private int port;


    private static final String BASE_URL = "/pagamento";

    private ExtractableResponse<Response> response;
    

    @Before
    public void setUp() {
        createStack();
    }
    
    @When("client post data pagamento")
    public void clientPostDataPagamento() {
        PagamentoRequest request = new PagamentoRequest(1L, 2L, "1000");
        response = given()
                .port(port)
                .header("Content-type", "application/json")
                .body(request)
                .when()
                .post(BASE_URL)
                .then()
                .extract();
    }

    @Then("the post client receives status code of {int}")
    public void clientPostDataPagamentoReceivesPostStatusCodeOf(int status) {
        assertEquals(status, response.statusCode());
    }

    @When("client patch payment confirmation of pagamentoId {string} and of pedidoId {string}")
    public void clientPatchConfirmPagamento(String pagamentoId, String pedidoId) {
        
        var patch_path = BASE_URL + String.format("/%s/pedido/%s/envia-confirmacao", pagamentoId, pedidoId);

        response = given()
                .port(port)
                .header("Content-type", "application/json")
                .when()
                .patch(patch_path)
                .then()
                .extract();
    }

    @Then("the patch client receives status code of {int}")
    public void clientPatchConfirmPagamentoReceivesStatusCodeOf(int status) {
        assertEquals(status, response.statusCode());
    }
}
