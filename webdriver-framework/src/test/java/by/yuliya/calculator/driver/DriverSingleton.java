package by.yuliya.calculator.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverSingleton {

    public DriverSingleton() {
    }

    protected ThreadLocal<RemoteWebDriver> threadDriver;

    public WebDriver getDriver() {
        if (null == threadDriver) {
            switch (System.getProperty("browser")) {
                case "opera": {
                    WebDriverManager.operadriver().setup();
                    OperaOptions options = new OperaOptions();
                    options.setBinary("C:\\Users\\Yuliya_Eibatava\\AppData\\Local\\Programs\\Opera\\60.0.3255.109\\opera.exe");
                    try {
                        threadDriver = new ThreadLocal<RemoteWebDriver>();
                        threadDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "edge": {
                    EdgeOptions options = new EdgeOptions();
                    WebDriverManager.edgedriver().setup();
                    try {
                        threadDriver = new ThreadLocal<RemoteWebDriver>();
                        threadDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    try {
                        threadDriver = new ThreadLocal<RemoteWebDriver>();
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

    public void closeDriver() {
        threadDriver = null;
        threadDriver.set(null);
    }

}
