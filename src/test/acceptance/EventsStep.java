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

public class EventsStep {
    public static WebDriver driver;

    @Before
    public void beforeScenario() {
        System.setProperty("webdriver.chrome.driver","/Library/Java/JUNIT/chromedriver");
        // ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        driver = new ChromeDriver();
        // Seems no more working in last Chrome versions
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("^je clique sur l'onglet \"([^\"]*)\"$")
    public void je_clique_sur_l_onglet(String arg1) throws Throwable {
        driver.get("https://www.tesla.com/fr_FR/");
        Actions action = new Actions(driver);
        WebElement burgerMenu = driver.findElement(By.className("tds-menu-header-main--trigger_icon"));
        action.click(burgerMenu);
        action.build().perform();
        Actions action2 = new Actions(driver);
        WebElement navEvenements = driver.findElement(By.xpath("/html/body/div[1]/div/header/div/div/nav/div[1]/nav[1]/ol/li[10]/a"));
        System.out.println("aa------ = " + navEvenements.getText());
        assertEquals(navEvenements.getText(), arg1);
        action2.click(navEvenements);
        action2.build().perform();
    }

    @Then("^je suis sur la page evenements$")
    public void je_suis_sur_la_page_evenements() throws Throwable {
        String currentUrl = driver.getCurrentUrl();
        assertEquals(currentUrl, "https://www.tesla.com/fr_FR/events");
    }

    @After
    public void afterScenario() {
        driver.quit();
    }
}
