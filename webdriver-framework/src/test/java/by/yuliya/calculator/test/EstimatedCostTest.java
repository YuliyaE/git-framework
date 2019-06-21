package by.yuliya.calculator.test;

import by.yuliya.calculator.model.CalculatorData;
import by.yuliya.calculator.page.CalculatorPage;
import by.yuliya.calculator.service.CalculatorDataCreator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;


public class EstimatedCostTest extends CommonConditions {

    protected static final Logger logger = LogManager.getLogger();

    @Test
    public void testCost() {
        CalculatorData data = CalculatorDataCreator.dataForCalculatorFields();
        CalculatorPage calculatorPage = new CalculatorPage(driver);
        String cost = calculatorPage
                .openPage()
                .estimateRentCost(data);
        logger.info(cost);
        Assert.assertEquals("Estimated Monthly Cost: USD 1,256.72", cost);
    }


}
