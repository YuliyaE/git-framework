package by.yuliya.calculator.test;

import by.yuliya.calculator.driver.DriverSingleton;
import by.yuliya.calculator.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class CommonConditions {

    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("environment", "dev");
        System.setProperty("browser", "opera");
        driver = (new DriverSingleton().getDriver());
    }

    @AfterTest
    public void tearDown(){
       driver.close();
    }
}
