package utils;

import com.aventstack.extentreports.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ExtentManager;

import static utils.ExtentManager.captureScreenshot;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    public static void attachScreenshot(String message) {

        String path = ExtentManager.captureScreenshot(TestListener.getTest().getModel().getName());

        try {
            TestListener.getTest().log(
                    Status.INFO,
                    message,
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
        attachScreenshot(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());
        attachScreenshot(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test Skipped");
    }



    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
