package testCases;

import base.AndroidBase;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.ExtentManager;
import utils.TestListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

@Listeners(TestListener.class)

public class homePageTest extends AndroidBase {

    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void initiateApp() throws IOException, InterruptedException, ParseException, org.json.simple.parser.ParseException {
        LaunchApp();


    }

    @Test(priority = 0)
    public void testHomePageTest() throws InterruptedException {
        homePage.clickDropdown();
        TestListener.getTest().info("Clicked on country dropdown");
        TestListener.attachScreenshot("CountryDropdown");

        homePage.selectCountry();
        TestListener.getTest().info("Selected country from dropdown");
        TestListener.attachScreenshot("SelectedCountry");

        homePage.enterName("Test");
        TestListener.getTest().info("Entered name in the text field");
        TestListener.attachScreenshot("EnteredName");

        homePage.selectGender();
        TestListener.getTest().info("Select gender from dropdown");
        TestListener.attachScreenshot("SelectedGender");

        homePage.clickLetsShop();
        TestListener.getTest().info("Clicked on Let's Shop button");
        TestListener.attachScreenshot("HomePage");

        Assert.assertEquals(homePage.verifyProductDisplayed(), "Products");

    }

    @Test(priority = 1)
    public void productListElementsTest() throws InterruptedException {
        homePage.addProductToCart("PG 3");
        TestListener.getTest().info("Added PG 3 to cart");
        TestListener.attachScreenshot("AddedPG3");

        homePage.addProductToCart("Air Jordan 4 Retro");
        TestListener.getTest().info("Added Air Jordan 4 Retro to cart");
        TestListener.attachScreenshot("AddedRetro");

        homePage.clickOnCart();
        TestListener.getTest().info("Clicked on cart icon");
        TestListener.attachScreenshot("CartPage");
        Assert.assertTrue(homePage.verifyCartPage(), "User is not on Cart Page");
        Assert.assertTrue(homePage.verifyProductInCart("PG 3"), "PG 3 is not added to cart");
        Assert.assertTrue(homePage.verifyProductInCart("Air Jordan 4 Retro"), "Retro is not added to cart");
    }

    @Test(priority = 2)
    public void verifyCheckboxTest() throws InterruptedException {
        homePage.checkEmailCheckbox();
        TestListener.getTest().info("Checked the email subscription checkbox");
        TestListener.attachScreenshot("CheckedCheckbox");

        homePage.clickProceed();
        TestListener.getTest().info("Clicked on Proceed button");
        TestListener.attachScreenshot("ProceedPage");

        homePage.switchToWebView();
        TestListener.getTest().info("Switched to WebView context");
        TestListener.attachScreenshot("WebViewContext");

        homePage.searchOnGoogle("Appium Testing");
        TestListener.getTest().info("");
        TestListener.attachScreenshot("SearchedGoogle");

        homePage.switchToNativeApp();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();

    }
}