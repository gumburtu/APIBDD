package petstore.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class TopfiytHomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Verdiğiniz XPath ile element tanımlaması
    @FindBy(xpath = "//ul[@id='main-menu']//a[@title='Topfiyt Kimler İçin?'][normalize-space()='Who is Topfiyt For?']")
    private WebElement whoIsTopfiytForLink;

    // Constructor
    public TopfiytHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    // Ana sayfaya gitme
    public void goToHomePage() {
        driver.get("https://www.topfiyt.com/");
        // Sayfa tam yüklenene kadar bekle
        wait.until(driver -> js.executeScript("return document.readyState").equals("complete"));
    }

    // Scroll pozisyonunu alma
    public long getCurrentScrollPosition() {
        return (Long) js.executeScript("return window.pageYOffset;");
    }

    // "Who is Topfiyt For?" linkine tıklama
    public void clickWhoIsTopfiytForLink() {
        // Başlangıç scroll pozisyonunu kaydet
        long initialScrollPosition = getCurrentScrollPosition();
        System.out.println("Başlangıç scroll pozisyonu: " + initialScrollPosition);

        // Element görünür ve tıklanabilir olana kadar bekle
        wait.until(ExpectedConditions.elementToBeClickable(whoIsTopfiytForLink));

        // Linke tıkla
        whoIsTopfiytForLink.click();

        // Kısa bir bekleme - scroll animasyonu için
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Link tıklandı");
    }

    // Scroll işleminin gerçekleşip gerçekleşmediğini kontrol etme
    public boolean hasPageScrolled(long initialPosition) {
        long currentPosition = getCurrentScrollPosition();
        System.out.println("Mevcut scroll pozisyonu: " + currentPosition);
        System.out.println("Başlangıç pozisyonu: " + initialPosition);

        // Pozisyon değişimi varsa scroll olmuş demektir
        return currentPosition > initialPosition;
    }

    // Sayfa scroll işlemini bekle ve doğrula
    public boolean waitForScrollAndValidate() {
        long initialPosition = getCurrentScrollPosition();

        // Maksimum 5 saniye bekle scroll için
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(100); // 100ms bekle
                if (hasPageScrolled(initialPosition)) {
                    return true;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }


    // Mevcut URL'yi alma (sayfa değişmediğini kontrol için)
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Sayfa yüksekliğini alma
    public long getPageHeight() {
        return (Long) js.executeScript("return document.body.scrollHeight;");
    }
}