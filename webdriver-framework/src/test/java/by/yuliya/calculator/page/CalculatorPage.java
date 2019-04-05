package by.yuliya.calculator.page;

import by.yuliya.calculator.model.DataForCalculator;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage extends AbstractPage {

    private final String PAGE_URL = "https://cloud.google.com/products/calculator/";

    private By computeEngine = By.xpath("//*[contains(text(),'Compute Engine')]");
    private By numberOfInstances = By.xpath("//input[@name='quantity']");
    private By operatingSystem = By.xpath("//md-select[@aria-label='Operating System / Software: Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS']");
    private By vmClass = By.xpath("//md-select[@placeholder='VM Class']");
    private By instanceType = By.xpath("//md-select[@placeholder='Instance type']");
    private By addGPUs = By.xpath("//div[@class='md-container md-ink-ripple']");
    private By numberOfGPUs = By.xpath("//md-select[@placeholder='Number of GPUs']");
    private By GPUType = By.xpath("//md-select[@placeholder='GPU type']");
    private By localSSD = By.xpath("//md-select[@placeholder='Local SSD']");
    private By datacenterLocation = By.xpath("//md-select[@placeholder='Datacenter location']");
    private By commitedUsage = By.xpath("//md-select[@placeholder='Committed usage']");
    private By estimate = By.xpath("//button[@class='md-raised md-primary cpc-button md-button md-ink-ripple' and text()='Add to Estimate']");
    private By rentCost = By.xpath("//*[contains(text(),'Total Estimated Cost:')]");
    private By emailEstimate = By.xpath("//button[@class='md-raised md-primary cpc-button md-button md-ink-ripple' and @id='email_quote']");
    private By emailInput = By.xpath("//input[@type='email']");
    private By sendEmail = By.xpath("//button[@aria-label='Send Email']");

    public CalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void clickComputeEngine() {
        driver.findElement(computeEngine).click();
    }

    public void setNumberOfInstances(DataForCalculator data) {
        driver.findElement(this.numberOfInstances).sendKeys(Integer.toString(data.getNumberOfInstances()));
    }

    public void setOperatingSystem() {
        driver.findElement(operatingSystem).click();
        driver.findElement(By.id("select_option_51")).click();
    }

    public void setVmClass() {
        driver.findElement(vmClass).click();
        driver.findElement(By.id("select_option_63")).click();
    }

    public void setInstanceType() {
        WebElement element = driver.findElement(instanceType);
        element.click();
        driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")).click();
    }

    public void setAddGPUs() {
        WebElement searchBtnAddGPUs = driver.findElement(addGPUs);
        searchBtnAddGPUs.click();
    }

    public void setNumberOfGPUs() {
        driver.findElement(numberOfGPUs).click();
        driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='1']")).click();
    }

    public void setGPUType() {
        driver.findElement(GPUType).click();
        driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='NVIDIA Tesla V100']")).click();
    }

    public void setLocalSSD() {
        driver.findElement(localSSD).click();
        driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='2x375 GB']")).click();
    }

    public void setDatacenterLocation() {
        driver.findElement(datacenterLocation).click();
        driver.findElement(By.xpath("//md-option[@id='select_option_199' and @value='europe-west3']")).click();
    }

    public void setCommitedUsage() {
        driver.findElement(commitedUsage).click();
        driver.findElement(By.xpath("//md-option[@id='select_option_103' and @value='1']")).click();
    }

    public void clickEstimate() {
        driver.findElement(estimate).click();
    }

    public String getRentCost() {
        driver.switchTo().frame("idIframe");
        WebElement element = driver.findElement(rentCost);
        return element.getText();
    }

    public void clickEmailEstimate() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfAllElementsLocatedBy(emailEstimate));
        driver.findElement(emailEstimate).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }


    public void clickSendEmail() {
        driver.findElement(sendEmail).click();
    }

    @Override
    public CalculatorPage openPage() {
        //GoogleCloudPlatformPage platformPage = new GoogleCloudPlatformPage(driver);
       // driver.navigate().to(platformPage.openPage().openCalculator());
        driver.navigate().to(PAGE_URL);
        logger.log(Level.INFO, "Calculator page opened");
        return this;
    }

    public CalculatorPage estimateRentCost(DataForCalculator data) {
        driver.switchTo().frame("idIframe");
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*")));
        clickComputeEngine();
        setNumberOfInstances(data);
        setOperatingSystem();
        setVmClass();
        setInstanceType();
        setAddGPUs();
        setNumberOfGPUs();
        setGPUType();
        setLocalSSD();
        setDatacenterLocation();
        setCommitedUsage();
        clickEstimate();
        driver.switchTo().defaultContent();
        return this;
    }

    public CalculatorPage confirmRentCostEstimation(String email) {
        driver.switchTo().frame("idIframe");
        clickEmailEstimate();
        enterEmail(email);
        clickSendEmail();
        driver.switchTo().defaultContent();
        return this;
    }

}
