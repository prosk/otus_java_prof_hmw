package ru.otus.aop.proxy;

import java.util.ArrayList;

public class ProxyDemo {
    public static void main(String[] args) {
        TestLogging myTestLogging = ProxyHelper.createMyClass();

        myTestLogging.calculate(1);
        myTestLogging.calculate(2, 3);
        myTestLogging.calculate(5, 6, "TestString");

        double res = myTestLogging.getSomeValue(true, -100.25, 200.25);

        ArrayList<Double> testDoubleList = new ArrayList<>();
        testDoubleList.add(123.123);
        testDoubleList.add(456.456);

        res = myTestLogging.getSomeValue(false, 0, 123.123, testDoubleList);

        System.out.println(myTestLogging);
    }
}



