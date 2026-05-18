package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import pages.ViewsPage;
import pages.homePage;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class AndroidBase{

    // Fixed: Changed from WebDriver to AppiumDriver for proper type matching

    public static AndroidDriver driver = null;
    public homePage homePage;
    public ViewsPage viewsPage;

     // Thread-safe driver
    private static final ThreadLocal<AndroidDriver> androidDriverThreadLocaldriver = new ThreadLocal<>();

    public AndroidDriver getDriver() {
        return androidDriverThreadLocaldriver.get();
    }


    public String readDataFromJsonFile (String data,String filePath) throws IOException, ParseException, org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        Object obj = null;
        JSONObject jsonObject = null;
        // filepath = "src/main/resources/data.json";
        obj = parser.parse(new FileReader(filePath));
        jsonObject = (JSONObject) obj;
        return (String) jsonObject.get(data);
    }

    public void LaunchApp() throws IOException, InterruptedException, ParseException, org.json.simple.parser.ParseException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(readDataFromJsonFile("dname","C:\\Users\\pinki\\IdeaProjects\\EcommAutomation\\src\\test\\java\\resources\\testData.json"));
        options.setPlatformName(readDataFromJsonFile("pname","C:\\Users\\pinki\\IdeaProjects\\EcommAutomation\\src\\test\\java\\resources\\testData.json"));
        options.setPlatformVersion("12");
        options.setAutomationName("UiAutomator2");   // IMPORTANT
        /*options.setAppPackage("com.androidsample.generalstore");
        options.setAppActivity("com.androidsample.generalstore.MainActivity");*/
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity("io.appium.android.apis.ApiDemos");
        //options.setCapability("appium:chromedriverAutodownload", true);
//        options.setCapability("appium:chromedriverExecutable",
//                "C:\\Users\\pinki\\Downloads\\chromedriver_win32\\chromedriver.exe");
         options.setNoReset(false);

        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, options);
        homePage = new homePage(driver);
        viewsPage = new ViewsPage(driver);

        Thread.sleep(5000);
        System.out.println("Application Started");

       // homePage = PageFactory.initElements(driver, homePage.class);
    }

    public void LaunchAppBrowserStack() throws MalformedURLException, InterruptedException {

        UiAutomator2Options options = new UiAutomator2Options();
        // App (already correct)
        options.setCapability("app", "bs://cf1c6cf88e32ae8248b4553429f936789a6ae6e7");
        // 🔥 Correct modern BrowserStack config
        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", "pinkikukreti_B8u4CK");
        bstackOptions.put("accessKey", "aQxpAdV2dJuyABssMS8q");
        bstackOptions.put("deviceName", "Google Pixel 7");
        bstackOptions.put("osVersion", "13.0");
        bstackOptions.put("projectName", "Appium Project");
        bstackOptions.put("buildName", "Build-1");
        bstackOptions.put("sessionName", "ViewsTest");

        options.setCapability("bstack:options", bstackOptions);

        URL url = new URL("https://hub.browserstack.com/wd/hub");

        driver = new AndroidDriver(url, options);

        homePage = new homePage(driver);
        viewsPage = new ViewsPage(driver);

        System.out.println("Application Started on BrowserStack");
        Thread.sleep(5000);

        // homePage = PageFactory.initElements(driver, homePage.class);
    }
    public void LaunchApp(String deviceName, String platformVersion, String udid, int port)
            throws MalformedURLException, InterruptedException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setPlatformName("Android");
        options.setPlatformVersion(platformVersion);
        options.setAutomationName("UiAutomator2");
        options.setUdid(udid);

        // IMPORTANT for parallel execution
        options.setCapability("appium:systemPort", port);

        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity("io.appium.android.apis.ApiDemos");

        URL url = new URL("http://127.0.0.1:4723");

        androidDriverThreadLocaldriver.set(new AndroidDriver(url, options));

        homePage = new homePage(getDriver());
        viewsPage = new ViewsPage(getDriver());

        System.out.println("App started on: " + deviceName);

        Thread.sleep(3000);
    }


     }

 /*   public void quitApp() {
        if (driver != null) {
            driver.quit();
        }*/
    //}

