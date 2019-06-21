package by.yuliya.calculator.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductAndServicesPage extends AbstractPage {

    private final String PAGE_URL = "https://cloud.google.com/pricing/";

    @FindBy(xpath = "//*[@class='cloud-button cloud-button--secondary' and @href='https://cloud.google.com/pricing/']")
    private WebElement inputSeePricing;

    public ProductAndServicesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public PricingPage clickSeePricing(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='cloud-button cloud-button--secondary' and @href='https://cloud.google.com/pricing/']")));
        inputSeePricing.click();
        return new PricingPage(driver);
    }

    @Override
    public ProductAndServicesPage openPage() {
        new GoogleCloudPlatformPage(driver).openPage().clickExploreProducts();
        logger.info("Product and services page opened");
        return this;
    }
}
