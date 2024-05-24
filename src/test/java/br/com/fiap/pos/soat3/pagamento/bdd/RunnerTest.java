package br.com.fiap.pos.soat3.pagamento.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;

@Tag("bdd-test")
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/resources",
        plugin = {"html:target/bdd/cucumber-report.html", "json:target/bdd/cucumber-report.json"},
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RunnerTest {
}
