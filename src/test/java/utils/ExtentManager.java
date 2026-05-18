package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

import static base.AndroidBase.driver;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setReportName("Mobile Automation Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "John Doe");
            extent.setSystemInfo("Environment", "QA");
        }

        return extent;
    }



    public static String captureScreenshot(String testName) {

        String timestamp = String.valueOf(System.currentTimeMillis());

        String relativePath = "./screenshots/" + testName + "_" + timestamp + ".png";
        String absolutePath = System.getProperty("user.dir") + "/reports/screenshots/"
                + testName + "_" + timestamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(absolutePath);

        try {
            FileUtils.copyFile(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return relativePath; // ✅ THIS FIXES YOUR ISSUE
    }


}