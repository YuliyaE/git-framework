package by.yuliya.calculator.test;

import by.yuliya.calculator.model.CalculatorData;
import by.yuliya.calculator.page.CalculatorPage;
import by.yuliya.calculator.service.CalculatorDataCreator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CostTest extends CommonConditions {

    @Test
    public void testCost() {
        CalculatorData data = CalculatorDataCreator.dataForCalculatorFields();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        String cost = calculatorPage
                .openPage()
                .estimateRentCost(data);
        System.out.println(cost + "  third");
        assertEquals("Estimated Monthly Cost: USD 776.72", cost);
    }


}
