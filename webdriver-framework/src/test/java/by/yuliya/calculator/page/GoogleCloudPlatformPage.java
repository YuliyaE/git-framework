package by.yuliya.calculator.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPlatformPage extends AbstractPage{

    private final String PAGE_URL = "https://cloud.google.com/";

    @FindBy(xpath = "//*[@class='cloud-button cloud-button--secondary' and @href='https://cloud.google.com/products/']")
    private WebElement inputExploreAllProducts;

    public GoogleCloudPlatformPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ProductAndServicesPage clickExploreProducts(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@class='cloud-button cloud-button--secondary' and @href='https://cloud.google.com/products/']")));
        inputExploreAllProducts.click();
        return new ProductAndServicesPage(driver);
    }

    @Override
    public GoogleCloudPlatformPage openPage() {
        driver.get(PAGE_URL);
        //logger.log(Level.INFO, "Google cloud platform page opened");
        return this;
    }

}
