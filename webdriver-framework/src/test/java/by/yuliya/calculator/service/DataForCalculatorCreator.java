package by.yuliya.calculator.service;

import by.yuliya.calculator.model.DataForCalculator;

public class DataForCalculatorCreator {

    public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.number.of.instances";


    public static DataForCalculator dataForFieldNumberOfInstances(){
        return new DataForCalculator(Integer.valueOf(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES)));
    }



}
