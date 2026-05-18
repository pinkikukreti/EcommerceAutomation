package base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class Utility extends AndroidBase {

    private AppiumDriver driver;
    public WebDriverWait wait;

    public Utility(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement scrollToText(String text) {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))"
                        + ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"));
    }

    public WebElement scrollToId(String id) {
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))"
                        + ".scrollIntoView(new UiSelector().resourceId(\"" + id + "\"))"));
    }

    // Wait until element is visible
    public WebElement waitForVisibility(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

        public void swipeOnElement(AppiumDriver driver, WebElement element, String direction, int duration) {

        int startX, endX, y;

        int left = element.getLocation().getX();
        int top = element.getLocation().getY();
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        y = top + height / 2;

        if (direction.equalsIgnoreCase("left")) {
            startX = left + (int)(width * 0.8);
            endX   = left + (int)(width * 0.2);

        } else if (direction.equalsIgnoreCase("right")) {
            startX = left + (int)(width * 0.2);
            endX   = left + (int)(width * 0.8);

        } else {
            throw new IllegalArgumentException("Invalid direction: " + direction + ". Use 'left' or 'right'.");
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new Pause(finger, Duration.ofMillis(200)));

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(), endX, y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    public void swipeLeft(AppiumDriver driver) {

        Dimension size = driver.manage().window().getSize();

        int startX = (int) (size.width * 0.8);
        int endX   = (int) (size.width * 0.2);
        int y      = size.height / 2;

        //swipeOnElement(driver, startX, y, endX, y, 500);
    }
    public void swipeRight(AppiumDriver driver) {

        Dimension size = driver.manage().window().getSize();

        int startX = (int) (size.width * 0.2);
        int endX   = (int) (size.width * 0.8);
        int y      = size.height / 2;

        //swipe(driver, startX, y, endX, y, 500);
    }
    public void longPressOnElement(AppiumDriver driver, WebElement element, int duration) {

        int centerX = element.getLocation().getX() + element.getSize().getWidth() / 2;
        int centerY = element.getLocation().getY() + element.getSize().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);

        // Move finger to element center
        longPress.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), centerX, centerY));

        // Finger down (press)
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // 🔥 Hold (this is the actual long press)
        longPress.addAction(new Pause(finger, Duration.ofMillis(duration)));

        // Release finger
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(longPress));
    }

    public void dragAndDrop(AppiumDriver driver, WebElement source, WebElement target, int duration) {

        int startX = source.getLocation().getX() + source.getSize().getWidth() / 2;
        int startY = source.getLocation().getY() + source.getSize().getHeight() / 2;

        int endX = target.getLocation().getX() + target.getSize().getWidth() / 2;
        int endY = target.getLocation().getY() + target.getSize().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence drag = new Sequence(finger, 1);

        // Move to source
        drag.addAction(finger.createPointerMove(Duration.ZERO,
                PointerInput.Origin.viewport(), startX, startY));

        // Press down
        drag.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // 🔥 Small hold to simulate real user
        drag.addAction(new Pause(finger, Duration.ofMillis(300)));

        // Drag to target
        drag.addAction(finger.createPointerMove(Duration.ofMillis(duration),
                PointerInput.Origin.viewport(), endX, endY));

        // Release
        drag.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(drag));
    }


}