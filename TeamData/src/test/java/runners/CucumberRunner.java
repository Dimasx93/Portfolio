package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // you also put a whole folder like features, not only specific feature files
        plugin = {"pretty", "html:cucumber-report/html-report.html",
                "junit:cucumber-report/junit-report.xml",
                "json:cucumber-report/json-report.json"},
        tags = "@userStory2",
        glue = {"stepDefinitions"}
)
public class CucumberRunner {

}
