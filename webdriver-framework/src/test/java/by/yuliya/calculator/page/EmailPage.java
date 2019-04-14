package by.yuliya.calculator.page;

import org.apache.logging.log4j.Level;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPage extends AbstractPage {

    private final String PAGE_URL = "https://10minutemail.com";
    private String currentURLOfEmail;

    @FindBy(id = "mailAddress")
    private WebElement mailAddress;

    @FindBy(id = "ui-id-1")
    private WebElement mail;

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
        new WebDriverWait(driver, 90).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='inc-mail-address']")));
        mail.click();
        WebElement element = driver.findElement(By.xpath("//*[contains(text(),'USD')]"));
        return element.getText();
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
