package petstore.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
//Cucumber ile junitin entegre bir şekilde feature file daki scenariolarimiiz çalıştırmayı sağlar.
@CucumberOptions(//Bu notasyon sayesinde hangi scenarioları çalıştıracağımızı, hangi raporları alacağımızı belirtiriz
        plugin = {
                "pretty",//console da scenariolarımız ile ilgili ayrıntılı bilgi almamızı sağlar
                "html:target/default-cucumber-report.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-reports/cucumber.xml",
        },
        features = "src/test/resources/features",
        glue = {"petstore/stepdefs", "petstore/hooks"},

        tags = "@US01SevalGet",


        dryRun = false,
        monochrome = false//true iken console daki çıktıları tek renk olarak verir
)
public class runners {
}