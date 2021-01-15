package test.acceptance;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"../src/test/acceptance/Configurator.feature"}, // ou se situe votre fichier .feature
        plugin = {"pretty"}
)
public class ConfiguratorTest {

}