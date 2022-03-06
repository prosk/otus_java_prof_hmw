package gunit.core;

import gunit.annotations.After;
import gunit.annotations.Before;
import gunit.annotations.Test;
import gunit.reflection.ReflectionHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private TestRunner() {
    }

    private static class AnnotatedMethods {
        List<String> beforeMethods = new ArrayList<>();
        List<String> testMethods = new ArrayList<>();
        List<String> afterMethods = new ArrayList<>();
    }

    public static void runAllTests(Class<?> clazz) {
        System.out.println("RUN TESTS FOR CLASS: " + clazz.getName());
        AnnotatedMethods annotatedMethods = getAnnotatedMethods(clazz);

        TestResult currentTestResult;
        TestRunStatistics runStatistics = new TestRunStatistics(clazz.getName());
        for(String testMethodName: annotatedMethods.testMethods) {
            currentTestResult = runTest(clazz, testMethodName,
                    annotatedMethods.beforeMethods, annotatedMethods.afterMethods);
            runStatistics.addTestResult(currentTestResult);
        }
        runStatistics.showReport();
    }

    private static TestResult runTest(Class<?> clazz, String testMethodName,
                                      List<String> beforeMethods, List<String> afterMethods) {
        System.out.println("START run test method: " + testMethodName);
        TestResult runResult = TestResult.FAILED;
        try {
            // Создаем экземпляр тестируемого класса
            Object clazzInstance = ReflectionHelper.instantiate(clazz);
            // Запуск методов в нужной последовательности
            runTestBeforeMethods(clazzInstance, beforeMethods);
            boolean isTestMethodSuccess = runTestMethod(clazzInstance, testMethodName);
            runTestAfterMethods(clazzInstance, afterMethods);
            if (isTestMethodSuccess) {
                runResult = TestResult.PASSED;
            }
        } catch (RuntimeException e) {
            System.out.println("Run Test Exception: " + e.getMessage());
            e.printStackTrace(System.out);
        }
        System.out.println("END run test method: " + testMethodName
                + " with result: " + runResult);
        return runResult;
    }

    private static void runTestBeforeMethods(Object clazzInstance, List<String> beforeMethods) {
        for(String beforeMethodName: beforeMethods) {
            ReflectionHelper.callMethod(clazzInstance, beforeMethodName);
        }
    }

    private static boolean runTestMethod(Object clazzInstance, String testMethodName) {
        boolean isTestMethodSuccess = false;
        try {
            ReflectionHelper.callMethod(clazzInstance, testMethodName);
            isTestMethodSuccess = true;
        } catch (RuntimeException e) {
            System.out.println("Test method Exception: ");
            e.printStackTrace(System.out);
        }
        return isTestMethodSuccess;
    }

    private static void runTestAfterMethods(Object clazzInstance, List<String> afterMethods) {
        for(String afterMethodName: afterMethods) {
            ReflectionHelper.callMethod(clazzInstance, afterMethodName);
        }
    }

    private static AnnotatedMethods getAnnotatedMethods(Class<?> clazz) {
        AnnotatedMethods annotatedMethods = new AnnotatedMethods();
        for(Method each: clazz.getDeclaredMethods()) {
            if (each.isAnnotationPresent(Before.class)) {
                annotatedMethods.beforeMethods.add(each.getName());
            }
            if (each.isAnnotationPresent(After.class)) {
                annotatedMethods.afterMethods.add(each.getName());
            }
            if (each.isAnnotationPresent(Test.class)) {
                annotatedMethods.testMethods.add(each.getName());
            }
        }
        if (annotatedMethods.testMethods.isEmpty()) {
            throw new IllegalArgumentException("Class " + clazz.getName() +
                    " has no methods with @Test annotation");
        }
        return annotatedMethods;
    }
}
