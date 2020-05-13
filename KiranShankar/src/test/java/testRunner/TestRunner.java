package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "./src/test/features",
        glue = {"stepDefinations"},
        dryRun = false,
        monochrome = true,
//        tags ={"@back-end"},
        strict = true,
        plugin = {
                "pretty",
                "html:target/Reports/cucumber-pretty",
                "json:target/cucumber/cucumber.json"
        })

public class TestRunner {

}
