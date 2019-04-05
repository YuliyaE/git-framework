package by.yuliya.calculator.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected WebDriver driver;

    public abstract AbstractPage openPage();
    public final int WAIT_TIMEOUT_SECONDS = 10;
    public static Logger logger = LogManager.getLogger();

    public AbstractPage(WebDriver driver)
    {
        this.driver = driver;
    }

}
