package by.yuliya.calculator.model;

import java.util.Objects;

public class DataForCalculator {

    private int numberOfInstances;

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public DataForCalculator(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataForCalculator data = (DataForCalculator) o;
        return numberOfInstances == data.getNumberOfInstances();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumberOfInstances());
    }
}
