package by.yuliya.calculator.page;

import org.apache.logging.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmailPage extends AbstractPage {

    // private final String PAGE_URL = "http://www.yopmail.com/ru/email-generator.php";
    private final String PAGE_URL = "https://10minutemail.com";
    // private By mailAddress = By.id("login");
    //  private By mail = By.xpath("//input[@value='Проверить почту']");
    private By mailAddress = By.id("mailAddress");
    private By mail = By.xpath("//span[@class='inc-mail-address']");

    public EmailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String getRandomEmail() {
        WebElement element = driver.findElement(mailAddress);
        return element.getAttribute("value");
    }

    public EmailPage readEmail() {
        new WebDriverWait(driver, 600) .until(ExpectedConditions.presenceOfAllElementsLocatedBy(mail));
        driver.findElement(mail).click();
        return this;
    }

    @Override
    public EmailPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.log(Level.INFO, "Email page opened");
        return this;
    }
}
