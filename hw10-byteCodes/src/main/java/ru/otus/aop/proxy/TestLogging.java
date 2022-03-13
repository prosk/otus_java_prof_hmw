package ru.otus.aop.proxy;

import java.util.List;

public interface TestLogging {
    void calculate(int param1);
    void calculate(int param1, int param2);
    void calculate(int param1, int param2, String param3);

    double getSomeValue(boolean isPositive, double minValue, double maxValue,
                               List<Double> excludeValues);
    double getSomeValue(boolean isPositive, double minValue, double maxValue);
}
