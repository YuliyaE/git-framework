package by.yuliya.calculator.test;

import by.yuliya.calculator.model.DataForCalculator;
import by.yuliya.calculator.page.CalculatorPage;
import by.yuliya.calculator.service.DataForCalculatorCreator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class FrameDataTest extends CommonConditions{

    @BeforeTest
    public void setUpForFrameTest() {
        DataForCalculator data = DataForCalculatorCreator.dataForFieldNumberOfInstances();
        new CalculatorPage(driver).openPage().estimateRentCost(data);
        driver.switchTo().frame("idIframe");
    }

    @Test
    public void testVMClass() {
        WebElement element = driver.findElement(By.id("select_option_63"));
        String actual = element.getAttribute("value");
        String expected = "regular";
        assertEquals(actual, expected);
    }

    @Test
    public void testInstanceType() {
        WebElement element = driver.findElement(By.xpath("//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']"));
        String actual = element.getAttribute("value");
        String expected = "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8";
        assertEquals(actual, expected);
    }

    @Test
    public void testRegion() {
        WebElement element = driver.findElement(By.xpath("//md-option[@value='europe-west3']"));
        String actual = element.getAttribute("value");
        String expected = "europe-west3";
        assertEquals(actual, expected);
    }

    @Test
    public void testLocalSSD() {
        WebElement element = driver.findElement(By.xpath("//md-option[@value='2']"));
        String actual = element.getAttribute("value");
        String expected = "2";
        assertEquals(actual, expected);
    }

    @Test
    public void testCommitmentTerm() {
        WebElement element = driver.findElement(By.xpath("//md-option[@value='1']"));
        String actual = element.getAttribute("value");
        String expected = "1";
        assertEquals(actual, expected);
    }

    @AfterTest
    public void tearDownForFrameTest() {
        driver.switchTo().defaultContent();
    }
}
