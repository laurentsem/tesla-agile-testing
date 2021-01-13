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


public class HomepageSteps {

    public static WebDriver driver;

    @Before
    public void beforeScenario() {
        System.setProperty("webdriver.chrome.driver","/Library/Java/JUNIT/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        // Seems no more working in last Chrome versions
        // driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Given("^je suis sur la page d'accueil$")
    public void je_suis_sur_la_page_d_accueil() throws Throwable {
        driver.get("https://www.tesla.com/fr_FR/");
    }

    @Then("^le titre doit être \"([^\"]*)\"$")
    public void le_titre_doit_être(String arg1) throws Throwable {
        assertEquals(driver.getTitle(), arg1);
    }

    @Then("^la description doit être \"([^\"]*)\"$")
    public void la_description_doit_être(String arg1) throws Throwable {
        assertEquals(driver.findElement(By.cssSelector("meta[name='description']")).getAttribute("content"), arg1);
    }

    @Then("^la punchline principale est \"([^\"]*)\"$")
    public void la_punchline_principale_est(String arg1) throws Throwable {
        WebElement punchlines = driver.findElement(By.className("tcl-hero-parallax"));
        List<WebElement> elementA = punchlines.findElements(By.tagName("h1"));
        for (WebElement e : elementA) {
            if (e.getText().equals(arg1)) {
                assertEquals(arg1, e.getText());
            }
        }
    }

    @Then("^le titre est \"([^\"]*)\"$")
    public void le_titre_est(String arg1) throws Throwable {
        WebElement nav = driver.findElement(By.className("tds-menu-header-nav--list"));
        List<WebElement> elementA = nav.findElements(By.tagName("a"));
        for (WebElement e : elementA) {
            if (e.getText().equals(arg1)) {
                assertEquals(arg1, e.getText());
            }
        }
    }

    @Then("^le lien associé à \"([^\"]*)\" est \"([^\"]*)\"$")
    public void le_lien_associé_à_est(String arg1, String arg2) throws Throwable {
        WebElement nav = driver.findElement(By.className("tds-menu-header-nav--list"));
        List<WebElement> elementA = nav.findElements(By.tagName("a"));
        for (WebElement e : elementA) {
            if (e.getText().equals(arg1)) {
                assertEquals(arg2, e.getAttribute("href"));
            }
        }
    }

    @When("^je clique sur le menu burger$")
    public void je_clique_sur_le_menu_burger() throws Throwable {
        Actions action = new Actions(driver);
        WebElement burgerMenu = driver.findElement(By.className("tds-menu-header-main--trigger_icon"));
        action.click(burgerMenu);
        action.build().perform();
    }

    @Then("^la catégorie est \"([^\"]*)\"$")
    public void la_catégorie_est(String arg1) throws Throwable {
        WebElement burgerNav = driver.findElement(By.id("block-hamburgerdesktop"));
        List<WebElement> elementA = burgerNav.findElements(By.tagName("a"));
        for (WebElement e : elementA) {
            if (e.getText().equals(arg1)) {
                assertEquals(arg1, e.getText());
                assertFalse(e.getAttribute("href").isEmpty());
            }
        }
    }


    @After
    public void afterScenario() {
        driver.quit();
    }
}
