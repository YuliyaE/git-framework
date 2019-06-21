package by.yuliya.calculator.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PricingPage extends AbstractPage {

    private final String PAGE_URL = "https://cloud.google.com/products/";

    @FindBy(xpath = "//*[@href='https://cloud.google.com/pricing/calculators' and @track-type='inPageNav']")
    private WebElement inputCalculators;

    public PricingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public CalculatorPage clickCalculator(){
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@href='https://cloud.google.com/pricing/calculators' and @track-type='inPageNav']")));
        inputCalculators.click();
        return new CalculatorPage(driver);
    }

    @Override
    public PricingPage openPage() {
        new ProductAndServicesPage(driver).openPage().clickSeePricing();
        logger.info("Pricing page opened");
        return this;
    }

}
