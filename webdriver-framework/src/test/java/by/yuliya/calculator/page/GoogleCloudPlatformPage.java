package by.yuliya.calculator.page;

import org.apache.logging.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPlatformPage extends AbstractPage{

    private final String PAGE_URL = "https://cloud.google.com/";

    private By inputExploreAllProducts = By.xpath("//*[@class='cloud-button cloud-button--primary' and @href='https://cloud.google.com/products/']") ;
    private By inputSeePricing = By.xpath("//*[@class='cloud-button cloud-button--secondary' and @href='https://cloud.google.com/pricing/']");
    private By inputCalculators = By.xpath("//*[@href='https://cloud.google.com/pricing/calculators' and @track-type='inPageNav']");

    public GoogleCloudPlatformPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String openCalculator(){
        driver.findElement(inputExploreAllProducts).click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(inputSeePricing));
        driver.findElement(inputSeePricing).click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(inputCalculators));
        driver.findElement(inputCalculators).click();
        return driver.getCurrentUrl();
    }

    @Override
    public GoogleCloudPlatformPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.log(Level.INFO, "Google cloud platform page opened");
        return this;
    }

}
