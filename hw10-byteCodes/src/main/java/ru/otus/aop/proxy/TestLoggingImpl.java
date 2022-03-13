package ru.otus.aop.proxy;


import ru.otus.aop.annotations.Log;

import java.util.Arrays;
import java.util.List;

public class TestLoggingImpl implements TestLogging {

    @Override
    public void calculate(int param1) {
        System.out.println("START calculate with one param (" + param1 + ")");
    }

    @Override
    @Log
    public void calculate(int param1, int param2) {
        System.out.println("START calculate with two params (" + param1 + " " + param2 + ")");
    }

    @Override
    public void calculate(int param1, int param2, String param3) {
        System.out.println("START calculate with three params (" + param1 + " "
                + param2 + " " + param3 + ")");
    }

    @Override
    @Log
    public double getSomeValue(boolean isPositive, double minValue, double maxValue, List<Double> excludeValues) {
        System.out.println("START getSomeValue with four params (" + isPositive + " "
                + minValue + " " + maxValue + " " + Arrays.toString(excludeValues.toArray()) + ")");
        return 0;
    }

    @Override
    public double getSomeValue(boolean isPositive, double minValue, double maxValue) {
        System.out.println("START getSomeValue with three params (" + isPositive + " "
                + minValue + " " + maxValue + ")");
        return 0;
    }

    @Override
    @Log
    public String toString() {
        return "TestLoggingImpl{}";
    }
}
