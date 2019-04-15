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
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*")));
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
        setCurrentURLOfCalculator();
        return getRentCost();
    }

    public EmailPage confirmRentCostEstimation(String email) {
        driver.switchTo().frame(FRAME_ID);
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
        switch (calculatorData.getInstanceType()) {
            case "Custom Machine Type":
                driver.findElement(By.xpath("//md-option[@value='custom']")).click();
                break;
            case "f1-micro    (vCPUs: shared, RAM: 0.60 GB)":
                driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-F1-MICRO']")).click();
                break;
            case "g1-small    (vCPUs: shared, RAM: 1.70 GB)":
                driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-G1-SMALL']")).click();
                break;
            case "n1-standard-1    (vCPUs: 1, RAM: 3.75 GB)":
                driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-1']")).click();
                break;
            case "n1-standard-2    (vCPUs: 2, RAM: 7.5 GB)":
                driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-2']")).click();
                break;
            case "n1-standard-4    (vCPUs: 4, RAM: 15 GB)":
                driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-4']")).click();
                break;
            case "n1-standard-8    (vCPUs: 8, RAM: 30 GB)":
                driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")).click();
                break;
            case "n1-standard-16    (vCPUs: 16, RAM: 60 GB)":
                driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-16']")).click();
                break;
            case "n1-standard-64    (vCPUs: 64, RAM: 240 GB)":
                driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-64']")).click();
                break;
            case "n1-standard-96    (vCPUs: 96, RAM: 360 GB)":
                driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-96']")).click();
                break;
        }
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
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='0']")).click();
                break;
            case 1:
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='1']")).click();
                break;
            case 2:
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='2']")).click();
                break;
            case 4:
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='4']")).click();
                break;
            case 8:
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='8']")).click();
                break;
        }

    }

    private void setGPUType(CalculatorData calculatorData) {
        GPUType.click();
        switch (calculatorData.getGPUType()) {
            case "NVIDIA Tesla K80":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='NVIDIA Tesla K80']")).click();
                break;
            case "NVIDIA Tesla P100":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='NVIDIA Tesla P100']")).click();
                break;
            case "NVIDIA Tesla P4":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='NVIDIA Tesla P4']")).click();
                break;
            case "NVIDIA Tesla V100":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='NVIDIA Tesla V100']")).click();
                break;
            case "NVIDIA Tesla T4":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='NVIDIA Tesla T4']")).click();
                break;
        }

    }

    private void setLocalSSD(CalculatorData calculatorData) {
        localSSD.click();
        switch (calculatorData.getLocalSSD()) {
            case "0":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='0']")).click();
                break;
            case "1x375 GB":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='1x375 GB']")).click();
                break;
            case "2x375 GB":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='2x375 GB']")).click();
                break;
            case "3x375 GB":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='3x375 GB']")).click();
                break;
            case "4x375 GB":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='4x375 GB']")).click();
                break;
            case "5x375 GB":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='5x375 GB']")).click();
                break;
            case "6x375 GB":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='6x375 GB']")).click();
                break;
            case "7x375 GB":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='7x375 GB']")).click();
                break;
            case "8x375 GB":
                driver.findElement(By.xpath("//div[@class='md-text ng-binding' and text()='8x375 GB']")).click();
                break;
        }
    }

    private void setDatacenterLocation(CalculatorData calculatorData) {
        datacenterLocation.click();
        switch (calculatorData.getDatacenterLocation()) {
            case "Iowa (us-central1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_192' and @value='us-central1']")).click();
                break;
            case "South Carolina (us-east1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_193' and @value='us-east1']")).click();
                break;
            case "Northern Virginia (us-east4)":
                driver.findElement(By.xpath("//md-option[@id='select_option_194' and @value='us-east4']")).click();
                break;
            case "Oregon (us-west1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_195' and @value='us-west1']")).click();
                break;
            case "Los Angeles (us-west2)":
                driver.findElement(By.xpath("//md-option[@id='select_option_196' and @value='us-west2']")).click();
                break;
            case "Belgium (europe-west1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_197' and @value='europe-west1']")).click();
                break;
            case "London (europe-west2)":
                driver.findElement(By.xpath("//md-option[@id='select_option_198' and @value='europe-west2']")).click();
                break;
            case "Frankfurt (europe-west3)":
                driver.findElement(By.xpath("//md-option[@id='select_option_199' and @value='europe-west3']")).click();
                break;
            case "Taiwan (asia-east1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_200' and @value='asia-east1']")).click();
                break;
            case "Hong Kong (asia-east2)":
                driver.findElement(By.xpath("//md-option[@id='select_option_201' and @value='asia-east2']")).click();
                break;
            case "Tokyo (asia-northeast1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_202' and @value='asia-northeast1']")).click();
                break;
            case "Osaka (asia-northeast2)":
                driver.findElement(By.xpath("//md-option[@id='select_option_203' and @value='asia-northeast2']")).click();
                break;
            case "Singapore (asia-southeast1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_204' and @value='asia-southeast1']")).click();
                break;
            case "Mumbai (asia-south1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_205' and @value='asia-south1']")).click();
                break;
            case "Sydney (australia-southeast1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_206' and @value='australia-southeast1']")).click();
                break;
            case "Sao Paulo (southamerica-east1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_207' and @value='southamerica-east1']")).click();
                break;
            case "Netherlands (europe-west4)":
                driver.findElement(By.xpath("//md-option[@id='select_option_208' and @value='europe-west4']")).click();
                break;
            case "Zurich (europe-west6)":
                driver.findElement(By.xpath("//md-option[@id='select_option_209' and @value='europe-west6']")).click();
                break;
            case "Finland (europe-north1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_210' and @value='europe-north1']")).click();
                break;
            case "Montréal, Canada (northamerica-northeast1)":
                driver.findElement(By.xpath("//md-option[@id='select_option_211' and @value='northamerica-northeast1']")).click();
                break;
        }
    }

    private void setCommitedUsage(CalculatorData calculatorData) {
        commitedUsage.click();
        switch (calculatorData.getCommitedUsage()) {
            case "None":
                driver.findElement(By.xpath("//md-option[@id='select_option_102' and @value='0']")).click();
                break;
            case "1 Year":
                driver.findElement(By.xpath("//md-option[@id='select_option_103' and @value='1']")).click();
                break;
            case "3 Years":
                driver.findElement(By.xpath("//md-option[@id='select_option_104' and @value='3']")).click();
                break;
        }
    }

    private void clickEstimate() {
        estimate.click();
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

    public CalculatorPage returnToCurrentCalculator(){
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
        logger.log(Level.INFO, "Calculator page opened");
        return this;
    }

}

