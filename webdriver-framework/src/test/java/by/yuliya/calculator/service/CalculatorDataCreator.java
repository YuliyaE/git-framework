package by.yuliya.calculator.service;

import by.yuliya.calculator.model.CalculatorData;

public class CalculatorDataCreator {

    public static final String TESTDATA_NUMBER_OF_INSTANCES = "testdata.number.of.instances";
    public static final String TESTDATA_OPERATING_SYSTEM = "testdata.operating.system";
    public static final String TESTDATA_VM_CLASS = "testdata.vm.class";
    public static final String TESTDATA_INSTANCE_TYPE = "testdata.instance.type";
    public static final String TESTDATA_ADD_GPUS = "testdata.add.gpus";
    public static final String TESTDATA_NUMBER_OF_GPUS = "testdata.number.of.gpus";
    public static final String TESTDATA_GPU_TYPE = "testdata.gpu.type";
    public static final String TESTDATA_LOCAL_SSD = "testdata.local.ssd";
    public static final String TESTDATA_DATACENTER_LOCATION = "testdata.datacenter.location";
    public static final String TESTDATA_COMMITED_USAGE = "testdata.commited.usage";

    public static CalculatorData dataForCalculatorFields(){
        return new CalculatorData(Integer.valueOf(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES)),
                TestDataReader.getTestData(TESTDATA_OPERATING_SYSTEM),
                TestDataReader.getTestData(TESTDATA_VM_CLASS),
                TestDataReader.getTestData(TESTDATA_INSTANCE_TYPE),
                Boolean.valueOf(TestDataReader.getTestData(TESTDATA_ADD_GPUS)),
                Integer.valueOf(TestDataReader.getTestData(TESTDATA_NUMBER_OF_GPUS)),
                TestDataReader.getTestData(TESTDATA_GPU_TYPE),
                TestDataReader.getTestData(TESTDATA_LOCAL_SSD),
                TestDataReader.getTestData(TESTDATA_DATACENTER_LOCATION),
                TestDataReader.getTestData(TESTDATA_COMMITED_USAGE));
    }

    public static CalculatorData dataForFieldWithoutGPUs(){
        return new CalculatorData(Integer.valueOf(TestDataReader.getTestData(TESTDATA_NUMBER_OF_INSTANCES)),
                TestDataReader.getTestData(TESTDATA_OPERATING_SYSTEM),
                TestDataReader.getTestData(TESTDATA_VM_CLASS),
                TestDataReader.getTestData(TESTDATA_INSTANCE_TYPE),
                TestDataReader.getTestData(TESTDATA_LOCAL_SSD),
                TestDataReader.getTestData(TESTDATA_DATACENTER_LOCATION),
                TestDataReader.getTestData(TESTDATA_COMMITED_USAGE));
    }



}
