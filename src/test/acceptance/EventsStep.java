package test.acceptance;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.cs.A;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en_old.Ac;
import gherkin.lexer.Th;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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
         ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
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
    @Then("^je choisi une ville \"([^\"]*)\"$")
    public void je_choisi_une_ville(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        WebElement inputCity = driver.findElement(By.id("edit-geoautocomplete"));
        try{
            Thread.sleep(5000);
            Actions actions = new Actions(driver);
            actions.sendKeys(inputCity,arg1);
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

    @Then("^il y a un lien Voir tous les événements$")
    public void il_y_a_un_lien_Voir_tous_les_événements() throws Throwable {
        WebElement lienAllEvents = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/main/div/div[1]/div/div/section[2]/div/div[1]/div[3]/div/form/div/div/span/a"));
        lienAllEvents.isDisplayed();
    }

    @Then("^il y a un lien Afficher plus$")
    public void il_y_a_un_lien_Afficher_plus() throws Throwable {
        WebElement lienShowMore = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/main/div/div[1]/div/div/section[2]/div/div[1]/div[7]/div/div/div[3]/ul/li/a"));
        lienShowMore.isDisplayed();
    }

    @Then("^il y a le champs \"([^\"]*)\"$")
    public void il_y_a_le_champs(String arg1) throws Throwable {
        WebElement form = driver.findElement(By.id("test-drive-form"));
        List<WebElement> list_champs = form.findElements(By.tagName("label"));
        for (WebElement e : list_champs){
            if(arg1.equals("Recevoir les News Tesla")){
                WebElement span = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/main/div/div[1]/div/div/section[2]/div/div[2]/div/div[1]/div/div/form/div/div[1]/div[8]/div/div/label/span"));
                assertThat(span.getAttribute("innerHTML"), containsString(arg1));
            }
            if(e.getText().contains(arg1)) {
                assertThat(e.getText(), containsString(arg1));
            }
            e.isDisplayed();

        }
    }

    @Then("^il y a un bouton \"([^\"]*)\"$")
    public void il_y_a_un_bouton(String arg1) throws Throwable {
        WebElement submitButton = driver.findElement(By.id("edit-submit-td-ajax"));
        assertEquals(arg1, submitButton.getAttribute("value"));
        submitButton.isDisplayed();
    }


    @Then("^je saisi le prénom \"([^\"]*)\"$")
    public void je_saisi_le_prénom(String arg1) throws Throwable {
        try {
            Thread.sleep(2000);
            WebElement form = driver.findElement(By.id("test-drive-form"));
            WebElement prenom = form.findElement(By.id("edit-firstname-td"));
            Actions action = new Actions(driver);
            action.sendKeys(prenom, arg1);
            action.build().perform();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^je saisi le nom \"([^\"]*)\"$")
    public void je_saisi_le_nom(String arg1) throws Throwable {
        try {
            Thread.sleep(2000);
            WebElement form = driver.findElement(By.id("test-drive-form"));
            WebElement nom = form.findElement(By.id("edit-lastname-td"));
            Actions action = new Actions(driver);
            action.sendKeys(nom, arg1);
            action.build().perform();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^je saisi le téléphone \"([^\"]*)\"$")
    public void je_saisi_le_téléphone(String arg1) throws Throwable {
        try {
            Thread.sleep(2000);
            WebElement form = driver.findElement(By.id("test-drive-form"));
            WebElement telephone = form.findElement(By.id("edit-phonenumber-td"));
            Actions action = new Actions(driver);
            action.sendKeys(telephone, arg1);
            action.build().perform();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^je saisi le region \"([^\"]*)\"$")
    public void je_saisi_le_region(String arg1) throws Throwable {
        try {
            Thread.sleep(2000);
            WebElement form = driver.findElement(By.id("test-drive-form"));
            Select region = new Select(form.findElement(By.name("countries_td")));
            region.selectByVisibleText(arg1);
            //        Actions action = new Actions(driver);
            //        action.sendKeys(prenom, arg1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^je saisi le code postal \"([^\"]*)\"$")
    public void je_saisi_le_code_postal(String arg1) throws Throwable {
        try {
            Thread.sleep(2000);
            WebElement form = driver.findElement(By.id("test-drive-form"));
            WebElement code_postal = form.findElement(By.id("edit-zipcode-td"));
            Actions action = new Actions(driver);
            action.sendKeys(code_postal, arg1);
            action.build().perform();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("^je clique sur suivant$")
    public void je_clique_sur_suivant() throws Throwable {
        WebElement submitButton = driver.findElement(By.id("edit-submit-td-ajax"));
        Actions actions = new Actions(driver);
        actions.click(submitButton);
        actions.build().perform();

    }

    @Then("^un message rouge apparaît avec le mot \"([^\"]*)\"$")
    public void un_message_rouge_apparaît_avec_le_mot(String arg1) throws Throwable {
        WebElement redMessage = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/main/div/div[1]/div/div/section[2]/div/div[2]/div/div[1]/div/div/form/div/div[1]/div[4]/div/ul/li"));
        redMessage.isDisplayed();
        assertEquals(redMessage.getCssValue("color"), "rgba(183, 65, 52, 1)");
        assertEquals(arg1, redMessage.getAttribute("innerHTML"));
    }

    @Then("^je vérifie la ville du premier événement affiché est \"([^\"]*)\"$")
    public void je_vérifie_la_ville_du_premier_événement_affiché_est(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        try{
            Thread.sleep(2000);
            WebElement location = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/main/div/div[1]/div/div/section[2]/div/div[1]/div[7]/div/div/div[2]/div/div/div/div[2]/div[1]"));
            assertThat(location.getAttribute("innerHTML"),containsString(arg1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @When("^je clique sur le bouton d'inscription$")
    public void je_clique_sur_le_bouton_d_inscription() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        try{
            Thread.sleep(3000);
            Actions actions = new Actions(driver);
            WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/main/div/div[1]/div/div/section[2]/div/div[1]/div[7]/div/div/div[2]/div[2]/div/div/div[2]/a"));
            actions.click(button);
            actions.build().perform();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    @Then("^je vérifie que je suis rédirigé sur le lien \"([^\"]*)\"$")
    public void je_vérifie_que_je_suis_rédirigé_sur_le_lien(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        try{
            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            assertThat(currentUrl,containsString(arg1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
