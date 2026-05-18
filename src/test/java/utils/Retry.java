package utils;

public class Retry implements org.testng.IRetryAnalyzer {

    private int retryCount = 0;
    private static final int maxRetryCount = 2; // Retry failed test 2 times

    @Override
    public boolean retry(org.testng.ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true; // Retry the test
        }
        return false; // Do not retry further
    }
}
