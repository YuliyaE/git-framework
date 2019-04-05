package by.yuliya.calculator.test;

import by.yuliya.calculator.model.DataForCalculator;
import by.yuliya.calculator.page.CalculatorPage;
import by.yuliya.calculator.page.EmailPage;
import by.yuliya.calculator.service.DataForCalculatorCreator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;


import static org.testng.Assert.assertEquals;

public class EstimatedCostTest extends CommonConditions {

    @Test
    public void testTotalEstimatedCostFromEmail() {

        DataForCalculator data = DataForCalculatorCreator.dataForFieldNumberOfInstances();
        CalculatorPage calculatorPage = new CalculatorPage(driver)
                .openPage()
                .estimateRentCost(data);

        String cost = calculatorPage.getRentCost();
        String parentWindowId = driver.getCurrentUrl();
        EmailPage emailPage = new EmailPage(driver);
        String email = emailPage
                .openPage()
                .getRandomEmail();

        String emailParentWindowId = driver.getCurrentUrl();

        driver.navigate().to(parentWindowId);
        calculatorPage.confirmRentCostEstimation(email);

        driver.navigate().to(emailParentWindowId);
        emailPage.readEmail();

        WebElement element = driver.findElement(By.xpath("//*[contains(text(),'USD')]"));
        String actual = element.getText();
        assertEquals(actual, cost);
    }

}
