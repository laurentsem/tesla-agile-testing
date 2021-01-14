package test.acceptance;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    @Given("^je suis sur la page de configuration Tesla Model S$")
    public void je_suis_sur_la_page_de_configuration_Tesla_Model_S() throws Throwable {
        driver.get("https://www.tesla.com/fr_fr/models/design#battery");
    }

    @Then("^le prix affiché par défaut est de \"([^\"]*)\" euros par mois$")
    public void le_prix_affiché_par_défaut_est_de_euros_par_mois(String arg1) throws Throwable {
        WebElement price = driver.findElement(By.cssSelector("p[class='finance-item--price finance-item--price-before-savings']"));
        assertThat(price.getText(), containsString(arg1));
    }

    @When("^je clique sur le bouton \"([^\"]*)\"$")
    public void je_clique_sur_le_bouton(String arg1) throws Throwable {
        Actions action = new Actions(driver);
        WebElement modelButton = driver.findElement(By.cssSelector("div[class='group--options_block m3-animate--all']"));
        if(modelButton.getAttribute("aria-label").equals(arg1)) {
            action.click(modelButton);
            action.build().perform();
        }
    }

    @Then("^le prix LOA est \"([^\"]*)\", \"([^\"]*)\" d'économies de carburant$")
    public void le_prix_LOA_est_d_économies_de_carburant(String arg1, String arg2) throws Throwable {
        WebElement price = driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div/div[3]/div/div/div/div[2]/div[1]/div/p"));
        WebElement savingCarburant = driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div/div[3]/div/div/div/div[2]/div[2]/p"));
        System.out.println("Prix LOA:" + price.getText());
        System.out.println("Economies carburant:" + savingCarburant.getText());
        assertThat(price.getText(), containsString(arg1));
        assertThat(savingCarburant.getText(), containsString(arg2));
    }

    @When("^je clique sur Voir détails$")
    public void je_clique_sur_Voir_détails() throws Throwable {
        try {
            Actions action = new Actions(driver);
            WebElement detailsButton = driver.findElement(By.className("finance-content--modal"));
            action.click(detailsButton);
            action.build().perform();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^le montant total est de \"([^\"]*)\"$")
    public void le_montant_total_est_de(String arg1) throws Throwable {
        WebElement totalPrice = driver.findElement(By.id("totalLeaseAmount"));
        assertThat(totalPrice.getAttribute("value"), containsString(arg1));
    }

//    @When("^je clique sur l'onglet Pilotage Automatique$")
//    public void je_clique_sur_l_onglet_Pilotage_Automatique() throws Throwable {
//        Actions action = new Actions(driver);
//        WebElement autoPilot = driver.findElement(By.className("packages-options--nav-item"));
//        driver.get("https://www.tesla.com/fr_fr/models/design#autopilot");
//        if(autoPilot.getAttribute("arial-label").equals("autopilot")) {
//            action.click(autoPilot);
//            action.build().perform();
//        }
//    }

    @When("^je clique sur le bouton Ajouter cette option$")
    public void je_clique_sur_le_bouton_Ajouter_cette_option() throws Throwable {
        driver.get("https://www.tesla.com/fr_fr/models/design#autopilot");
        Actions action = new Actions(driver);
        WebElement addOption = driver.findElement(By.cssSelector("div[class='group--options_card--checkbox--container']"));
        action.click(addOption);
        action.build().perform();
    }

    @Then("^le prix affiché par défaut \"([^\"]*)\" euros augmente de \"([^\"]*)\" euros$")
    public void le_prix_affiché_par_défaut_euros_augmente_de_euros(String arg1, String arg2) throws Throwable {
        WebElement afterPrice = driver.findElement(By.cssSelector("p[class='finance-item--price finance-item--price-before-savings']"));
        int priceDiff = Integer.parseInt(arg1) + Integer.parseInt(arg2);
        assertThat(afterPrice.getText(), containsString(String.valueOf(priceDiff)));
    }

    @When("^je clique sur le logo Tesla en haut à gauche$")
    public void je_clique_sur_le_logo_Tesla_en_haut_à_gauche() throws Throwable {
       // Actions actions = new Actions(driver);
        WebElement logo = driver.findElement(By.cssSelector("a[class='tsla-header-main--logo tds-icon tds-icon-wordmark']"));
        String href = logo.getAttribute("href");
        driver.get(href+"fr_fr");
    }

    @When("^je clique sur \"([^\"]*)\"$")
    public void je_clique_sur(String arg1) throws Throwable {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 4000)");
        Actions actions = new Actions(driver);
        WebElement footer = driver.findElement(By.cssSelector("ol[class='tds-footer-meta tds-footer--centered tds-footer-nav tds-list']"));
        List<WebElement> listFooter = footer.findElements(By.tagName("li"));
        for (WebElement li : listFooter) {
            WebElement a = li.findElement(By.tagName("a"));
            System.out.println("a texte = " + a.getAttribute("innerHTML") );
            System.out.println(arg1);
            if(a.getAttribute("innerHTML").equals(arg1)){
                System.out.println("a texte dans if = " + a.getAttribute("innerHTML") );
                actions.click(a);
                actions.build().perform();
            }
        }
    }

    @Then("^j'arrive sur la page \"([^\"]*)\"$")
    public void j_arrive_sur_la_page(String arg1) throws Throwable {
        try {
            Thread.sleep(3000);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("url = " + currentUrl);
            assertEquals(arg1, currentUrl);
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
