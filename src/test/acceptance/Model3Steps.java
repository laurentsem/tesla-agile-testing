package test.acceptance;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.cs.A;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en_old.Ac;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class Model3Steps {
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

    @Given("^je suis sur la page model(\\d+)$")
    public void je_suis_sur_la_page_model(int arg1) throws Throwable {
        driver.get("https://www.tesla.com/fr_fr/model3");
    }

    @When("^je sélectionne \"([^\"]*)\"$")
    public void je_sélectionne(String arg1) throws Throwable {
        WebElement caracteristiques = driver.findElement(By.className("side_nav-container"));
        List<WebElement> li = caracteristiques.findElements(By.tagName("li"));
        for(WebElement e : li){
            if (e.getAttribute("data-title").equals(arg1)){
                Actions actions = new Actions(driver);
                actions.click(e);
                actions.build().perform();
            }
        }
    }

    @When("^je clique sur la gamme est \"([^\"]*)\"$")
    public void je_clique_sur_la_gamme_est(String arg1) throws Throwable {
        Actions actions = new Actions(driver);
        WebElement gamme = driver.findElement(By.cssSelector("nav[class='tds-tab-list tcl-tabs__list']"));
        List<WebElement> a = gamme.findElements(By.tagName("a"));
        for (WebElement e : a){
            if (e.getAttribute("data-gtm-interaction").equals(arg1)){
                actions.click(e);
                actions.build().perform();
            }
        }
    }

    @When("^le poids est \"([^\"]*)\"$")
    public void le_poids_est(String arg1) throws Throwable {
        WebElement poids = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/div/div/div/section[8]/div/section/div/div[2]/div/div/section[1]/div[2]/ul/li[1]/span[2]"));
        String poids2 = poids.getAttribute("innerHTML");
        System.out.println("Avant: " + poids2);
        poids2.replaceAll("&nbsp;","");
        System.out.println("Après: " + poids2);
        assertEquals(arg1, poids2);
    }

    @When("^l'accélération est \"([^\"]*)\"$")
    public void l_accélération_est(String arg1) throws Throwable {
        WebElement acceleration = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/div/div/div/section[8]/div/section/div/div[2]/div/div/section[1]/div[2]/ul/li[1]/span[2]"));
        String acceleration2 = acceleration.getAttribute("innerHTML");
        acceleration2.replace("&nbsp;","");
        assertThat(acceleration2,containsString(arg1));
    }

    @When("^l'autonomie est \"([^\"]*)\"$")
    public void l_autonomie_est(String arg1) throws Throwable {
        WebElement autonomie = driver.findElement(By.xpath("/html/body/div[1]/main/div/div[2]/div/div/div/div/section[8]/div/section/div/div[2]/div/div/section[1]/div[2]/ul/li[1]/span[2]"));
        String autonomie2 = autonomie.getAttribute("innerHTML");
        assertEquals(arg1, autonomie2);
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
