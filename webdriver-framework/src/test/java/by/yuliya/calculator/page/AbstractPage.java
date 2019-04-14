package by.yuliya.calculator.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected WebDriver driver;

    public abstract AbstractPage openPage();
    protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected static Logger logger = LogManager.getLogger();

    protected AbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

}
