package by.yuliya.calculator.test;

import by.yuliya.calculator.model.CalculatorData;
import by.yuliya.calculator.page.CalculatorPage;
import by.yuliya.calculator.page.EmailPage;
import by.yuliya.calculator.service.CalculatorDataCreator;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EstimatedCostTest extends CommonConditions {

    /*@Test
    public void testTotalEstimatedCostFromEmail() {
        CalculatorData data = CalculatorDataCreator.dataForCalculatorFields();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        String cost = calculatorPage
                .openPage()
                .estimateRentCost(data);
        EmailPage emailPage = new EmailPage(driver);
        String email = emailPage
                .openPage()
                .getRandomEmail();
        calculatorPage
                .returnToCurrentCalculator()
                .confirmRentCostEstimation(email);
        assertEquals(emailPage.openCurrentEmail().getEstimatedCost(), cost);
    }*/

    @Test
    public void testTotalEstimatedCostFromEmail() {
        CalculatorData data = CalculatorDataCreator.dataForCalculatorFields();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        String cost = calculatorPage
                .openPage()
                .estimateRentCost(data);
        System.out.println(cost + "  first");
        assertEquals("Estimated Monthly Cost: USD 776.72", cost);
    }

}
