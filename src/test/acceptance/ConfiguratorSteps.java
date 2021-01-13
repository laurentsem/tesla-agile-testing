package test.acceptance;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ConfiguratorSteps {
    public static WebDriver driver;

    @Before
    public void beforeScenario() {
        System.setProperty("webdriver.chrome.driver","/Library/Java/JUNIT/chromedriver");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        driver = new ChromeDriver();
        // Seems no more working in last Chrome versions
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Given("^je suis sur la page du modèle Tesla Model S$")
    public void je_suis_sur_la_page_du_modèle_Tesla_Model_S() throws Throwable {
        driver.get("https://www.tesla.com/fr_fr/models");
        String parentWindowHandle = driver.getWindowHandle();
        System.out.println(parentWindowHandle);
    }

    @When("^je clique sur le bouton commander$")
    public void je_clique_sur_le_bouton_commander() throws Throwable {
        Actions action = new Actions(driver);
        WebElement commanderButton = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/div/div/div/section[1]/div/section/div/div[1]/div/div[2]/div/div[6]/a"));
        action.click(commanderButton);
        action.build().perform();
    }

    @Then("^j'accède à la page de configuration Tesla Model S$")
    public void j_accède_à_la_page_de_configuration_Tesla_Model_S() throws Throwable {
        try {
            Thread.sleep(6000);
            for(String winHandle : driver.getWindowHandles())
            {
                driver.switchTo().window(winHandle);
            }
            String configUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + configUrl);
            assertEquals(configUrl, "https://www.tesla.com/fr_fr/models/design#battery");
            // driver.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @After
    public void afterScenario() {
        try {
            Thread.sleep(3000);
            driver.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
