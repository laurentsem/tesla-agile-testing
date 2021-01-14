package test.acceptance;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"../src/test/acceptance/Model3.feature"}, // ou se situe votre fichier .feature
        plugin = {"pretty"}
)
public class Model3Test {
}

