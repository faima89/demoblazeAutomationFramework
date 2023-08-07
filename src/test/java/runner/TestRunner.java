package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources",
		glue = "steps",
		dryRun = false,
		monochrome = true,
		plugin = {"pretty", "html:target/report.html"} 
		)
public class TestRunner {

}
