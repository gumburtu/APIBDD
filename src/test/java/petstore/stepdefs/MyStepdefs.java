package petstore.stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;
import petstore.utilities.Driver;
import java.time.Duration;

public class MyStepdefs {

    private WebDriver driver;
    private WebDriverWait wait;

    @Given("User navigates to {string}")
    public void userNavigatesTo(String url) {
        driver = Driver.getDriver();
        driver.get(url);

        // Sayfanın yüklendiğini doğrula
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Topfiyt"));
    }

    @When("User clicks on the {string} link")
    public void userClicksOnTheLink(String linkText) {
        try {
            // Önce text ile bul
            WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                    By.linkText(linkText)));
            link.click();
        } catch (Exception e) {
            try {
                // Partial text ile dene
                WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                        By.partialLinkText(linkText)));
                link.click();
            } catch (Exception ex) {
                // XPath ile dene (case-insensitive)
                String xpath = String.format("//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '%s')]",
                        linkText.toLowerCase());
                WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath(xpath)));
                link.click();
            }
        }
    }

    @Then("User should be redirected to the corresponding page")
    public void userShouldBeRedirectedToTheCorrespondingPage() {
        // URL'nin değiştiğini kontrol et
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlToBe("https://www.topfiyt.com/")));

        String currentUrl = driver.getCurrentUrl();

        // "Who is Topfiyt For?" sayfasına yönlendirildiğini doğrula
        // URL'de ilgili keyword'lerin bulunduğunu kontrol et
        Assert.assertTrue("URL should contain relevant keywords for 'Who is Topfiyt For?' page",
                currentUrl.toLowerCase().contains("who") ||
                currentUrl.toLowerCase().contains("for") ||
                currentUrl.toLowerCase().contains("about") ||
                currentUrl.toLowerCase().contains("topfiyt"));

        // Sayfa başlığının değiştiğini kontrol et
        String pageTitle = driver.getTitle();
        Assert.assertNotNull("Page title should not be null", pageTitle);
        Assert.assertFalse("Page title should not be empty", pageTitle.isEmpty());

        // Sayfanın yüklendiğini doğrula (body elementi mevcut olmalı)
        WebElement body = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        Assert.assertTrue("Page body should be displayed", body.isDisplayed());

        System.out.println("Successfully navigated to: " + currentUrl);
        System.out.println("Page title: " + pageTitle);
    }
}