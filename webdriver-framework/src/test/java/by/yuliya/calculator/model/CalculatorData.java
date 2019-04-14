package by.yuliya.calculator.model;

import java.util.Objects;

public class CalculatorData {

    private int numberOfInstances;
    private String operatingSystem;
    private String vmClass;
    private String instanceType;
    private boolean addGPUs;
    private int numberOfGPUs;
    private String GPUType;
    private String localSSD;
    private String datacenterLocation;
    private String commitedUsage;

    public CalculatorData(int numberOfInstances, String operatingSystem, String vmClass, String instanceType, boolean addGPUs, int numberOfGPUs, String GPUType, String localSSD, String datacenterLocation, String commitedUsage) {
        this.numberOfInstances = numberOfInstances;
        this.operatingSystem = operatingSystem;
        this.vmClass = vmClass;
        this.instanceType = instanceType;
        this.addGPUs = addGPUs;
        this.numberOfGPUs = numberOfGPUs;
        this.GPUType = GPUType;
        this.localSSD = localSSD;
        this.datacenterLocation = datacenterLocation;
        this.commitedUsage = commitedUsage;
    }

    public CalculatorData(int numberOfInstances, String operatingSystem, String vmClass, String instanceType, String localSSD, String datacenterLocation, String commitedUsage) {
        this.numberOfInstances = numberOfInstances;
        this.operatingSystem = operatingSystem;
        this.vmClass = vmClass;
        this.instanceType = instanceType;
        this.localSSD = localSSD;
        this.datacenterLocation = datacenterLocation;
        this.commitedUsage = commitedUsage;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getVmClass() {
        return vmClass;
    }

    public void setVmClass(String vmClass) {
        this.vmClass = vmClass;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public boolean isAddGPUs() {
        return addGPUs;
    }

    public void setAddGPUs(boolean addGPUs) {
        this.addGPUs = addGPUs;
    }

    public int getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public void setNumberOfGPUs(int numberOfGPUs) {
        this.numberOfGPUs = numberOfGPUs;
    }

    public String getGPUType() {
        return GPUType;
    }

    public void setGPUType(String GPUType) {
        this.GPUType = GPUType;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public void setDatacenterLocation(String datacenterLocation) {
        this.datacenterLocation = datacenterLocation;
    }

    public String getCommitedUsage() {
        return commitedUsage;
    }

    public void setCommitedUsage(String commitedUsage) {
        this.commitedUsage = commitedUsage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculatorData data = (CalculatorData) o;
        return numberOfInstances == data.numberOfInstances &&
                addGPUs == data.addGPUs &&
                numberOfGPUs == data.numberOfGPUs &&
                Objects.equals(operatingSystem, data.operatingSystem) &&
                Objects.equals(vmClass, data.vmClass) &&
                Objects.equals(instanceType, data.instanceType) &&
                Objects.equals(GPUType, data.GPUType) &&
                Objects.equals(localSSD, data.localSSD) &&
                Objects.equals(datacenterLocation, data.datacenterLocation) &&
                Objects.equals(commitedUsage, data.commitedUsage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfInstances, operatingSystem, vmClass, instanceType, addGPUs, numberOfGPUs, GPUType, localSSD, datacenterLocation, commitedUsage);
    }
}
