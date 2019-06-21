package by.yuliya.calculator.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSingleton {


    private static WebDriver driver;

    private DriverSingleton() {
    }

    protected static ThreadLocal<RemoteWebDriver> threadDriver;

    @BeforeTest
    public static WebDriver getDriver() {

        if (null == threadDriver) {
            switch (System.getProperty("browser")) {
                case "opera": {
                    WebDriverManager.operadriver().setup();
                    OperaOptions options = new OperaOptions();
                    options.setBinary("C:\\Users\\Yuliya_Eibatava\\AppData\\Local\\Programs\\Opera\\60.0.3255.109\\opera.exe");
                    threadDriver = new ThreadLocal<RemoteWebDriver>();
                    try {
                        threadDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "edge": {
                    EdgeOptions options = new EdgeOptions();
                    WebDriverManager.edgedriver().setup();
                    threadDriver = new ThreadLocal<RemoteWebDriver>();
                    try {
                        threadDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    threadDriver = new ThreadLocal<RemoteWebDriver>();
                    try {
                        threadDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;


                }

            }
            threadDriver.get().manage().window().maximize();
        }
        return threadDriver.get();
    }

    @AfterTest
    public static void closeDriver() throws InterruptedException {
        //  Thread.sleep(3000);
        //driver.quit();
        //  driver = null;
        threadDriver.set(null);
    }
}
