package test.acceptance;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    @When("^je clique sur le menu$")
    public void je_clique_sur_le_menu() throws Throwable {
        driver.get("https://www.tesla.com/fr_FR/");
        Actions action = new Actions(driver);
        WebElement burgerMenu = driver.findElement(By.className("tds-menu-header-main--trigger_icon"));
        action.click(burgerMenu);
        action.build().perform();
    }

    @When("^je clique sur l'onglet \"([^\"]*)\"$")
    public void je_clique_sur_l_onglet(String arg1) throws Throwable {
        Actions action2 = new Actions(driver);
        WebElement navEvenements = driver.findElement(By.xpath("/html/body/div[1]/div/header/div/div/nav/div[1]/nav[1]/ol/li[10]/a"));
        System.out.println("aa------ = " + navEvenements.getAttribute("innerHTML"));
        assertEquals(arg1,navEvenements.getAttribute("innerHTML"));
        try{
            Thread.sleep(1000);
            action2.click(navEvenements);
            action2.build().perform();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Then("^je suis sur la page evenements$")
    public void je_suis_sur_la_page_evenements() throws Throwable {
        try{
            Thread.sleep(1000);
            String currentUrl = driver.getCurrentUrl();
            assertEquals( "https://www.tesla.com/fr_FR/events",currentUrl);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Then("^je suis sur la page events$")
    public void je_suis_sur_la_page_events() throws Throwable {
        driver.get("https://www.tesla.com/fr_FR/events");
    }
    @Then("^je choisi une ville$")
    public void je_choisi_une_ville() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement inputCity = driver.findElement(By.id("edit-geoautocomplete"));
        try{
            Thread.sleep(5000);
            Actions actions = new Actions(driver);
            actions.sendKeys(inputCity,"Hong Kong");
            actions.build().perform();
            Thread.sleep(1000);
            Actions actions1 = new Actions(driver);
            actions1.sendKeys(Keys.RETURN);
            actions1.build().perform();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Then("^je vérifie s'il y a (\\d+) evenements$")
    public void je_vérifie_s_il_y_a_evenements(int arg1) throws Throwable {
        WebElement content = driver.findElement(By.className("view-content"));
        List<WebElement> list = content.findElements(By.tagName("div"));
        int count = 0;
        for(WebElement e : list){
            if(e.getAttribute("class").contains("views-row"))
            count++;
        }
        assertEquals(arg1,count);
    }




    @After
    public void afterScenario() {
        try{
            Thread.sleep(5000);
            driver.quit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
