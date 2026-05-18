package testCases;

import base.AndroidBase;
import org.testng.annotations.*;
import utils.TestListener;

import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

@Listeners(TestListener.class)

public class ViewsTest extends AndroidBase {


    @BeforeMethod
    public void initiateApp() throws InterruptedException, IOException, ParseException, org.json.simple.parser.ParseException {
        //LaunchAppBrowserStack();
        LaunchApp();
    }

    /*@Parameters({"deviceName", "platformVersion", "udid", "systemPort"})
    @BeforeMethod
    public void setup(String deviceName, String platformVersion, String udid, int systemPort) throws Exception {
        LaunchApp(deviceName, platformVersion, udid, systemPort);
    }*/

    @Test(priority = 0,retryAnalyzer = utils.Retry.class)
    public void navigateToViewsTest() throws InterruptedException {
        viewsPage.tapOnViews();
        TestListener.getTest().info("Clicked on Views");
        TestListener.attachScreenshot("ViewsPage");
        viewsPage.tapOnGallery();
        TestListener.getTest().info("Clicked on Gallery");
        TestListener.attachScreenshot("GalleryPage");
        viewsPage.tapOnPhotos();
        TestListener.getTest().info("Clicked on Photos");
        TestListener.attachScreenshot("PhotosPage");
        viewsPage.swipeOnGallery();
        TestListener.getTest().info("Swiped on Gallery");
        TestListener.attachScreenshot("SwipedGallery");

    }

    @Test(priority = 1)
    public void navigateToExpandableListsTest() throws InterruptedException {
        viewsPage.tapOnViews();
        TestListener.getTest().info("Clicked on Views");
        TestListener.attachScreenshot("ViewsPage");
        viewsPage.tapOnExpandableLists();
        TestListener.getTest().info("Clicked on Expandable Lists");
        TestListener.attachScreenshot("ExpandableListsPage");
        viewsPage.tapOnCustomAdapter();
        TestListener.getTest().info("Clicked on Custom Adapter");
        TestListener.attachScreenshot("CustomAdapterPage");
        viewsPage.longPressOnPeopleNames();
        TestListener.getTest().info("Long pressed on People Names");
        TestListener.attachScreenshot("LongPressedPeopleNames");
    }

    @Test(priority = 3)
    public void dragAndDropTest() throws InterruptedException {
        //driver.navigate().back();
        //driver.navigate().back();
        viewsPage.tapOnViews();
        TestListener.getTest().info("Clicked on Views");
        TestListener.attachScreenshot("ViewsPage");
        viewsPage.dragAndDrop();
        TestListener.getTest().info("Performed drag and drop");
        TestListener.attachScreenshot("DragAndDrop");
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();

    }
}
