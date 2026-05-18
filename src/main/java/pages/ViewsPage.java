package pages;

import base.AndroidBase;
import base.Utility;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ViewsPage extends AndroidBase {

    private AndroidDriver driver;
    Utility ul;



    public ViewsPage(AndroidDriver driver) {
        this.driver = driver;
        ul = new Utility(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }


    // Locators
@AndroidFindBy(accessibility = "Views")
    public WebElement clickOnViews;

    @AndroidFindBy(accessibility = "Gallery")
    public WebElement clickOnGallery;

    @AndroidFindBy(accessibility = "1. Photos")
    public WebElement clickOnPhotos;

    @AndroidFindBy(id = "io.appium.android.apis:id/gallery")
    public WebElement getClickOnGallery;

    @AndroidFindBy(accessibility = "Expandable Lists")
    public WebElement clickOnExpandableLists;

    @AndroidFindBy(accessibility = "1. Custom Adapter")
    public WebElement clickOnCustomAdapter;

   @AndroidFindBy(xpath = "//android.widget.TextView[@text='People Names']")
   public WebElement longPressOnPeople;


   @AndroidFindBy(accessibility = "Drag and Drop")
   public WebElement clickOnDragAndDrop;

   @AndroidFindBy(id="io.appium.android.apis:id/drag_dot_1")
   public WebElement DragDot1;

   @AndroidFindBy(id="io.appium.android.apis:id/drag_dot_2")
   public WebElement DragDot2;


    //Actions
    public void tapOnViews() {
        clickOnViews.click();
    }
    public void tapOnGallery() {
        clickOnGallery.click();
    }

    public void tapOnPhotos() {
        clickOnPhotos.click();
    }

    public void swipeOnGallery() {
        //clickOnFirstImage.click();
        ul.swipeOnElement(driver,getClickOnGallery, "left", 500);
        ul.swipeOnElement(driver,getClickOnGallery, "left", 500);
        ul.swipeOnElement(driver,getClickOnGallery, "right", 500);
    }

    public void tapOnExpandableLists() {
        clickOnExpandableLists.click();
    }

        public void tapOnCustomAdapter() {
            clickOnCustomAdapter.click();
        }

        public void longPressOnPeopleNames() {
            ul.longPressOnElement(driver, longPressOnPeople, 3000);
        }

        public void dragAndDrop() {
        clickOnDragAndDrop.click();
        ul.dragAndDrop(driver, DragDot1,DragDot2, 500);
        }

}

