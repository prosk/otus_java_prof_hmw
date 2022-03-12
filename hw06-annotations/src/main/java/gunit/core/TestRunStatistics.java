package gunit.core;

public class TestRunStatistics {
    private String className;
    private int totalTestCount = 0;
    private int passedTestCount = 0;
    private int failedTestCount = 0;

    public TestRunStatistics(String className) {
        this.className = className;
    }

    public void addTestResult(TestResult testResult) {
        if(testResult == TestResult.PASSED) {
            passedTestCount++;
        }else if(testResult == TestResult.FAILED) {
            failedTestCount++;
        }
        totalTestCount++;
    }

    public void showReport() {
        System.out.println("TEST RESULTS");
        System.out.println("-------------------------------------------------------------");
        System.out.println("Test Class Name: " + className);
        System.out.println("TOTAL Test Count: " + totalTestCount);
        System.out.println("PASSED Test Count: " + passedTestCount);
        System.out.println("FAILED Test Count: " + failedTestCount);
        System.out.println("-------------------------------------------------------------");
    }
}
