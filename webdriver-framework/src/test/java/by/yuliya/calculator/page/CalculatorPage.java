package by.yuliya.calculator.page;

import by.yuliya.calculator.model.CalculatorData;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage extends AbstractPage {

    private final String PAGE_URL = "https://cloud.google.com/products/calculator/";
    private final String FRAME_ID = "idIframe";
    private static final String REGEX_SPACE = "\\s";
    private String currentURLOfCalculator;

    @FindBy(xpath = "//*[contains(text(),'Compute Engine')]")
    private WebElement computeEngine;

    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//md-select[@id='select_61']")
    private WebElement operatingSystem;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    private WebElement vmClass;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement instanceType;

    @FindBy(xpath = "//div[@class='md-container md-ink-ripple']")
    private WebElement addGPUs;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUs;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement GPUType;

    @FindBy(xpath = "//md-select[@placeholder='Local SSD']")
    private WebElement localSSD;

    @FindBy(xpath = "//md-select[@placeholder='Datacenter location']")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    private WebElement commitedUsage;

    @FindBy(xpath = "//button[@class='md-raised md-primary cpc-button md-button md-ink-ripple' and text()='Add to Estimate']")
    private WebElement estimate;

    @FindBy(xpath = "//button[@class='md-raised md-primary cpc-button md-button md-ink-ripple hide-sm' and contains(text(), 'Save Estimate')]")
    private WebElement saveEstimate;

    @FindBy(xpath = "//*[contains(text(),'Total Estimated Cost:')]")
    private WebElement rentCost;

    @FindBy(xpath = "//button[@class='md-raised md-primary cpc-button md-button md-ink-ripple' and @id='email_quote']")
    private WebElement emailEstimate;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmail;

    public CalculatorPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public String estimateRentCost(CalculatorData calculatorData) {
        driver.switchTo().frame(FRAME_ID);
        clickComputeEngine();
        setNumberOfInstances(calculatorData);
        setOperatingSystem(calculatorData);
        setVmClass(calculatorData);
        setInstanceType(calculatorData);
        setAddGPUs(calculatorData);
        setNumberOfGPUs(calculatorData);
        setGPUType(calculatorData);
        setLocalSSD(calculatorData);
        setDatacenterLocation(calculatorData);
        setCommitedUsage(calculatorData);
        clickEstimate();
        saveEstimate();
        setCurrentURLOfCalculator();
        return getRentCost();
    }

    public EmailPage confirmRentCostEstimation(String email) {
        driver.switchTo().frame(FRAME_ID);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='md-raised md-primary cpc-button md-button md-ink-ripple' and @id='email_quote']")));
        clickEmailEstimate();
        enterEmail(email);
        clickSendEmail();
        return new EmailPage(driver);
    }

    private void clickComputeEngine() {
        computeEngine.click();
    }

    private void setNumberOfInstances(CalculatorData calculatorData) {
        numberOfInstances.sendKeys(Integer.toString(calculatorData.getNumberOfInstances()));
    }

    private void setOperatingSystem(CalculatorData calculatorData) {
        operatingSystem.click();
        switch (calculatorData.getOperatingSystem()) {
            case "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS":
                driver.findElement(By.id("select_option_51")).click();
                break;
            case "Paid: Windows Server 2008r2, Windows Server 2012r2, Windows Server 2016, Windows Core":
                driver.findElement(By.id("select_option_52")).click();
                break;
            case "Paid: Red Hat Enterprise Linux":
                driver.findElement(By.id("select_option_53")).click();
                break;
            case "Paid: Red Hat Enterprise Linux for SAP Applications":
                driver.findElement(By.id("select_option_54")).click();
                break;
            case "Paid: Red Hat Enterprise Linux for SAP with HA and Update Services":
                driver.findElement(By.id("select_option_55")).click();
                break;
            case "Paid: SUSE":
                driver.findElement(By.id("select_option_56")).click();
                break;
            case "Paid: SLES for SAP":
                driver.findElement(By.id("select_option_57")).click();
                break;
            case "Paid: SQL Server Standard (2012, 2014, 2016)":
                driver.findElement(By.id("select_option_58")).click();
                break;
            case "Paid: SQL Server Web (2012, 2014, 2016)":
                driver.findElement(By.id("select_option_59")).click();
                break;
            case "Paid: SQL Server Enterprise (2012, 2014, 2016) ":
                driver.findElement(By.id("select_option_60")).click();
                break;
        }
    }

    private void setVmClass(CalculatorData calculatorData) {
        vmClass.click();
        switch (calculatorData.getVmClass()) {
            case "regular":
                driver.findElement(By.id("select_option_63")).click();
                break;
            case "preemptible":
                driver.findElement(By.id("select_option_64")).click();
        }
    }

    private void setInstanceType(CalculatorData calculatorData) {
        instanceType.click();
        driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-" + (calculatorData.getInstanceType().split(REGEX_SPACE)[0]).toUpperCase() + "']")).click();
    }

    private void setAddGPUs(CalculatorData calculatorData) {
        if (calculatorData.isAddGPUs()) {
            addGPUs.click();
        }
    }

    private void setNumberOfGPUs(CalculatorData calculatorData) {
        numberOfGPUs.click();
        driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='" + calculatorData.getNumberOfGPUs() + "']")).click();
    }

    private void setGPUType(CalculatorData calculatorData) {
        GPUType.click();
        driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='" + calculatorData.getGPUType() + "']")).click();
    }

    private void setLocalSSD(CalculatorData calculatorData) {
        localSSD.click();
        driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='" + calculatorData.getLocalSSD() + "']")).click();
    }

    private void setDatacenterLocation(CalculatorData calculatorData) {
        datacenterLocation.click();
        switch (calculatorData.getDatacenterLocation()) {
            case "Iowa (us-central1)":
                driver.findElement(By.id("select_option_192")).click();
                break;
            case "South Carolina (us-east1)":
                driver.findElement(By.id("select_option_193")).click();
                break;
            case "Northern Virginia (us-east4)":
                driver.findElement(By.id("select_option_194")).click();
                break;
            case "Oregon (us-west1)":
                driver.findElement(By.id("select_option_195")).click();
                break;
            case "Los Angeles (us-west2)":
                driver.findElement(By.id("select_option_196")).click();
                break;
            case "Belgium (europe-west1)":
                driver.findElement(By.id("select_option_197")).click();
                break;
            case "London (europe-west2)":
                driver.findElement(By.id("select_option_198")).click();
                break;
            case "Frankfurt (europe-west3)":
                driver.findElement(By.id("select_option_199")).click();
                break;
            case "Taiwan (asia-east1)":
                driver.findElement(By.id("select_option_200")).click();
                break;
            case "Hong Kong (asia-east2)":
                driver.findElement(By.id("select_option_201")).click();
                break;
            case "Tokyo (asia-northeast1)":
                driver.findElement(By.id("select_option_202")).click();
                break;
            case "Osaka (asia-northeast2)":
                driver.findElement(By.id("select_option_203")).click();
                break;
            case "Singapore (asia-southeast1)":
                driver.findElement(By.id("select_option_204")).click();
                break;
            case "Mumbai (asia-south1)":
                driver.findElement(By.id("select_option_205")).click();
                break;
            case "Sydney (australia-southeast1)":
                driver.findElement(By.id("select_option_206")).click();
                break;
            case "Sao Paulo (southamerica-east1)":
                driver.findElement(By.id("select_option_207")).click();
                break;
            case "Netherlands (europe-west4)":
                driver.findElement(By.id("select_option_208")).click();
                break;
            case "Zurich (europe-west6)":
                driver.findElement(By.id("select_option_209")).click();
                break;
            case "Finland (europe-north1)":
                driver.findElement(By.id("select_option_210")).click();
                break;
            case "Montréal, Canada (northamerica-northeast1)":
                driver.findElement(By.id("select_option_211")).click();
                break;
        }
    }

    private void setCommitedUsage(CalculatorData calculatorData) {
        commitedUsage.click();
        switch (calculatorData.getCommitedUsage()) {
            case "None":
                driver.findElement(By.id("select_option_102")).click();
                break;
            case "1 Year":
                driver.findElement(By.id("select_option_103")).click();
                break;
            case "3 Years":
                driver.findElement(By.id("select_option_104")).click();
                break;
        }
    }

    private void clickEstimate() {
        estimate.click();
    }

    private void saveEstimate() {
        saveEstimate.click();
    }

    public String getRentCost() {
        String cost = rentCost.getText();
        return cost.replace("Total Estimated", "Estimated Monthly").replace(" per 1 month", "");
    }

    public String getCurrentURLOfCalculator() {
        return currentURLOfCalculator;
    }

    public void setCurrentURLOfCalculator() {
        this.currentURLOfCalculator = driver.getCurrentUrl();
    }

    public CalculatorPage returnToCurrentCalculator() {
        driver.navigate().to(getCurrentURLOfCalculator());
        return this;
    }

    private void clickEmailEstimate() {
        emailEstimate.click();
    }

    private void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    private void clickSendEmail() {
        sendEmail.click();
    }

    @Override
    public CalculatorPage openPage() {
        new PricingPage(driver).openPage().clickCalculator();
        driver.navigate().to(PAGE_URL);
        logger.log(Level.INFO, "Calculator page opened");
        return this;
    }

}

