package by.yuliya.calculator.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;


import org.testng.ITestResult;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class TestListener implements ITestListener{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onTestStart(ITestResult iTestResult) {


        LOGGER.info("Start testing");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult)  {
        LOGGER.error("Step failed. See screenshots.");
        saveScreenshot();
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOGGER.info("Test finished");
    }


  private void saveScreenshot()  {
      /*try {
          ReportPortalMessage message = new ReportPortalMessage(new File(".//target/screenshots/"
                  + getCurrentTimeAsString() +
                  ".png"), "screenshot");
          LOGGER.info(message);
      } catch (IOException e) {
          e.printStackTrace();
      }*/

  }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

}
