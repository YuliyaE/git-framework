package by.yuliya.calculator.page;

import by.yuliya.calculator.model.CalculatorData;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @FindBy(xpath = "//*[contains(text(),'Compute Engine')]")
    private WebElement computeEngine;

    @FindBy(xpath = "//input[@name='quantity']")
    private WebElement numberOfInstances;

    @FindBy(xpath = "//md-select[@id='select_64']")
    private WebElement operatingSystem;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    private WebElement vmClass;

    @FindBy(id = "select_75")
    private WebElement machineType;

    @FindBy(xpath = "//div[@class='md-container md-ink-ripple']")
    private WebElement addGPUs;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUs;

    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    private WebElement GPUType;

    @FindBy(id = "select_value_label_49")
    private WebElement localSSD;

    @FindBy(id = "select_79")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    private WebElement commitedUsage;

    @FindBy(xpath = "//button[@class='md-raised md-primary cpc-button md-button md-ink-ripple' and text()='Add to Estimate']")
    private WebElement estimate;

    @FindBy(xpath = "//button[@aria-label='Add to Estimate']")
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
        logger.info("The calculator form is opened");
        clickComputeEngine();
        setNumberOfInstances(calculatorData);
        setOperatingSystem(calculatorData);
        setVmClass(calculatorData);
        setMachineType(calculatorData);
        setAddGPUs(calculatorData);
       // setNumberOfGPUs(calculatorData);
      //  setGPUType(calculatorData);
        setLocalSSD(calculatorData);
     //   setDatacenterLocation(calculatorData);
       // setCommitedUsage(calculatorData);
        clickEstimate();
        setCurrentURLOfCalculator();
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Add to Estimate']")));
        saveEstimate();

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
        logger.info("Number of instances is " + calculatorData.getNumberOfInstances());
    }

    private void setOperatingSystem(CalculatorData calculatorData) {
        operatingSystem.click();
        switch (calculatorData.getOperatingSystem()) {
            case "Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS":
                driver.findElement(By.id("select_option_54")).click();
                break;
            case "Paid: Windows Server 2008r2, Windows Server 2012r2, Windows Server 2016, Windows Core":
                driver.findElement(By.id("select_option_55")).click();
                break;
            case "Paid: Red Hat Enterprise Linux":
                driver.findElement(By.id("select_option_56")).click();
                break;
            case "Paid: Red Hat Enterprise Linux for SAP Applications":
                driver.findElement(By.id("select_option_57")).click();
                break;
            case "Paid: Red Hat Enterprise Linux for SAP with HA and Update Services":
                driver.findElement(By.id("select_option_58")).click();
                break;
            case "Paid: SUSE":
                driver.findElement(By.id("select_option_59")).click();
                break;
            case "Paid: SLES for SAP":
                driver.findElement(By.id("select_option_60")).click();
                break;
            case "Paid: SQL Server Standard (2012, 2014, 2016)":
                driver.findElement(By.id("select_option_61")).click();
                break;
            case "Paid: SQL Server Web (2012, 2014, 2016)":
                driver.findElement(By.id("select_option_62")).click();
                break;
            case "Paid: SQL Server Enterprise (2012, 2014, 2016) ":
                driver.findElement(By.id("select_option_63")).click();
                break;
        }
    }

    private void setVmClass(CalculatorData calculatorData) {
        vmClass.click();
        switch (calculatorData.getVmClass()) {
            case "regular":
                driver.findElement(By.id("select_option_66")).click();
                break;
            case "preemptible":
                driver.findElement(By.id("select_option_67")).click();
        }
    }

    private void setMachineType(CalculatorData calculatorData) {
        machineType.click();
        driver.findElement(By.id("select_option_211")).click();
        // driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-" + (calculatorData.getInstanceType().split(REGEX_SPACE)[0]).toUpperCase() + "']")).click();
    }

    private void setAddGPUs(CalculatorData calculatorData) {
        if (calculatorData.isAddGPUs()) {
            addGPUs.click();
        }
    }

    private void setNumberOfGPUs(CalculatorData calculatorData) {
        numberOfGPUs.click();
        switch (calculatorData.getNumberOfGPUs()) {
            case 0:
                driver.findElement(By.id("select_option_340")).click();
                break;
            case 1:
                driver.findElement(By.id("select_option_341")).click();
                break;
            case 2:
                driver.findElement(By.id("select_option_342")).click();
                break;
            case 4:
                driver.findElement(By.id("select_option_343")).click();
                break;
            case 8:
                driver.findElement(By.id("select_option_344")).click();
                break;
        }
    }

    private void setGPUType(CalculatorData calculatorData) {
        GPUType.click();
        switch (calculatorData.getGPUType()) {
            case "NVIDIA Tesla K80":
                driver.findElement(By.id("select_option_345")).click();
                break;
            case "NVIDIA Tesla P100":
                driver.findElement(By.id("select_option_346")).click();
                break;
            case "NVIDIA Tesla P4":
                driver.findElement(By.id("select_option_347")).click();
                break;
            case "NVIDIA Tesla V100":
                driver.findElement(By.id("select_option_348")).click();
                break;
            case "NVIDIA Tesla T4":
                driver.findElement(By.id("select_option_349")).click();
                break;
        }
    }

    private void setLocalSSD(CalculatorData calculatorData) {
        localSSD.click();
        new Actions(driver).click(driver.findElement(By.id("select_option_169"))).build().perform();
    }

    private void setDatacenterLocation(CalculatorData calculatorData) {
        datacenterLocation.click();
        switch (calculatorData.getDatacenterLocation()) {
            case "Iowa (us-central1)":
                driver.findElement(By.id("select_option_174")).click();
                break;
            case "South Carolina (us-east1)":
                driver.findElement(By.id("select_option_175")).click();
                break;
            case "Northern Virginia (us-east4)":
                driver.findElement(By.id("select_option_176")).click();
                break;
            case "Oregon (us-west1)":
                driver.findElement(By.id("select_option_177")).click();
                break;
            case "Los Angeles (us-west2)":
                driver.findElement(By.id("select_option_178")).click();
                break;
            case "Belgium (europe-west1)":
                driver.findElement(By.id("select_option_179")).click();
                break;
            case "London (europe-west2)":
                driver.findElement(By.id("select_option_180")).click();
                break;
            case "Frankfurt (europe-west3)":
                driver.findElement(By.id("select_option_181")).click();
                break;
            case "Taiwan (asia-east1)":
                driver.findElement(By.id("select_option_182")).click();
                break;
            case "Hong Kong (asia-east2)":
                driver.findElement(By.id("select_option_183")).click();
                break;
            case "Tokyo (asia-northeast1)":
                driver.findElement(By.id("select_option_184")).click();
                break;
            case "Osaka (asia-northeast2)":
                driver.findElement(By.id("select_option_185")).click();
                break;
            case "Singapore (asia-southeast1)":
                driver.findElement(By.id("select_option_186")).click();
                break;
            case "Mumbai (asia-south1)":
                driver.findElement(By.id("select_option_187")).click();
                break;
            case "Sydney (australia-southeast1)":
                driver.findElement(By.id("select_option_188")).click();
                break;
            case "Sao Paulo (southamerica-east1)":
                driver.findElement(By.id("select_option_189")).click();
                break;
            case "Netherlands (europe-west4)":
                driver.findElement(By.id("select_option_190")).click();
                break;
            case "Zurich (europe-west6)":
                driver.findElement(By.id("select_option_191")).click();
                break;
            case "Finland (europe-north1)":
                driver.findElement(By.id("select_option_192")).click();
                break;
            case "Montréal, Canada (northamerica-northeast1)":
                driver.findElement(By.id("select_option_193")).click();
                break;
        }
    }

    private void setCommitedUsage(CalculatorData calculatorData) {
        commitedUsage.click();
        switch (calculatorData.getCommitedUsage()) {
            case "None":
                driver.findElement(By.id("select_option_81")).click();
                break;
            case "1 Year":
                driver.findElement(By.id("select_option_82")).click();
                break;
            case "3 Years":
                driver.findElement(By.id("select_option_83")).click();
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

    public void setCurrentURLOfCalculator() {
        System.out.println(driver.getCurrentUrl());
        this.currentURLOfCalculator = driver.getCurrentUrl();
    }

    public CalculatorPage returnToCurrentCalculator() {
        driver.navigate().back();
        //driver.navigate().to(getCurrentURLOfCalculator());
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
        //new PricingPage(driver).openPage().clickCalculator();
        driver.navigate().to(PAGE_URL);
        logger.info("Calculator page opened");
        return this;
    }

}

