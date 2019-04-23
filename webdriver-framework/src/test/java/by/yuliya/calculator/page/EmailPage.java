package by.yuliya.calculator.page;

import org.apache.logging.log4j.Level;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPage extends AbstractPage {

    private final String PAGE_URL = "https://10minutemail.net/?lang=ru";

    private String currentURLOfEmail;

    @FindBy(id = "fe_text")
    private WebElement mailAddress;

    private final By mailLocator = By.xpath("//a[text()='Google Cloud Platform Price Estimate']");

    @FindBy(xpath = "//*[contains(text(),'USD')]")
    private WebElement message;

    public EmailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getRandomEmail() {
        WebElement element = mailAddress;
        setCurrentURLOfEmail();
        return element.getAttribute("value");
    }

    public String getEstimatedCost() {
        WebElement  mail = new WebDriverWait(driver, 90)
                .until(ExpectedConditions.presenceOfElementLocated(mailLocator));
        mail.click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'USD')]")));
        return message.getText();
    }

    public String getCurrentURLOfEmail() {
        return currentURLOfEmail;
    }

    public void setCurrentURLOfEmail() {
        this.currentURLOfEmail = driver.getCurrentUrl();
    }

    public EmailPage openCurrentEmail() {
        driver.navigate().to(getCurrentURLOfEmail());
        return this;
    }

    @Override
    public EmailPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.log(Level.INFO, "Email page opened");
        return this;
    }
}
