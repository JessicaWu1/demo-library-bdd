package net.greenbone.demolibrary.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "json:test-reports/output.json"}, //output format auf json
        glue = "net.greenbone.demolibrary.bdd", //dependency injection framework, wo liegen die klassen
        features = "src/test/resources/features/", //liegen die bdd tests
        dryRun = false //tests starten und alle schritte die in den features definiert sind auch angegeben
        //strict = true
)
public class RunBDDTests {
}
