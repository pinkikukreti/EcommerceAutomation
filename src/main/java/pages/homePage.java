package pages;

import base.Utility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class homePage {

    private AndroidDriver driver;
    Utility ul;

    // Constructor
    public homePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        ul = new Utility(driver);
    }

    // Locators
    @AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
    public WebElement countryDropdown;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    public WebElement textField;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/radioFemale")
    public WebElement radioFemale;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    public WebElement letsShopButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    public WebElement verifyProductPage;

    // ✅ IMPORTANT: Use List here
    @AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
    public List<WebElement> productList;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productAddCart")
    public List<WebElement> addToCartButtons;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/appbar_btn_cart")
    public WebElement cartButton;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    public WebElement verifyCartPage;

    @AndroidFindBy(xpath = "//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")
    public WebElement emailCheckbox;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    public WebElement proceedButton;

    // Actions
    public void clickDropdown() {
        countryDropdown.click();
    }

    public void selectCountry() {
        ul.scrollToText("Aruba").click();
    }

    public void enterName(String name) {
        textField.sendKeys(name);
    }

    public void selectGender() {
        radioFemale.click();
    }

    public void clickLetsShop() {
        letsShopButton.click();
    }
    public String verifyProductDisplayed() throws InterruptedException {
        //ul.waitForVisibility(verifyProductPage, 10);
        String productPageTitle = verifyProductPage.getText();
        System.out.println("Product Page Title: " + productPageTitle);
        return productPageTitle;
    }

    // ✅ MAIN METHOD
    public void addProductToCart(String productName) throws InterruptedException {

        boolean productFound = false;

        List<String> seenProducts = new java.util.ArrayList<>();

        while (!productFound) {

            for (int i = 0; i < productList.size(); i++) {

                String currentProduct = productList.get(i).getText();

                seenProducts.add(currentProduct);

                if (currentProduct.equalsIgnoreCase(productName)) {


                    ul.waitForVisibility(addToCartButtons.get(i), 5);
                    addToCartButtons.get(i).click();
                    System.out.println("Clicked on: " + productName);
                    productFound = true;
                    break;
                }
            }

            // Scroll if not found
            if (!productFound) {
                boolean canScroll = ul.scrollToText(productName) != null;

                if (!canScroll) {
                    throw new RuntimeException("Product not found: " + productName);
                }
            }
        }
    }

    public void clickOnCart() {
        ul.waitForVisibility(cartButton, 5);
        cartButton.click();
    }

    public Boolean verifyCartPage() {
        return verifyCartPage.isDisplayed();
    }


    public boolean verifyProductInCart(String productName) {

        for (WebElement product : productList) {

            String name = product.getText();

            if (name.equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void checkEmailCheckbox() {
        if (!emailCheckbox.isSelected()) {
            emailCheckbox.click();
        }


    }

    public void clickProceed() {
        proceedButton.click();
    }


    // ✅ Switch TO WebView

    public void switchToWebView() throws InterruptedException {

        Thread.sleep(3000);

        Set<String> contexts = ((SupportsContextSwitching) driver).getContextHandles();

        System.out.println("=== Available Contexts ===");
        for (String context : contexts) {
            System.out.println("Context: " + context);
        }

        for (String context : contexts) {
            if (context.contains("WEBVIEW")) {
                // ✅ Correct method for Appium 8.x/9.x
                ((SupportsContextSwitching) driver).context(context);
                System.out.println("✅ Switched to WebView: " + context);
                driver.findElement(By.xpath("//button[@aria-label ='Toggle navigation bar']")).click();
                return;
            }
        }

        throw new RuntimeException("❌ No WebView context found!");
    }

    public void switchToNativeApp() {
        ((SupportsContextSwitching) driver).context("NATIVE_APP");
        System.out.println("✅ Switched back to NATIVE_APP");
    }

    // Locator for WebView container (Native context)
    @AndroidFindBy(id = "com.androidsample.generalstore:id/webView")
    public WebElement webView;

    // ✅ Search on Google inside WebView
    public void searchOnGoogle(String searchText) throws InterruptedException {

        // Step 1: Switch to WebView
        switchToWebView();

        // Step 2: Now use Selenium/Web locators
        // Google search box locator
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.click();
        searchBox.sendKeys(searchText);
        searchBox.sendKeys(Keys.RETURN);

        System.out.println("✅ Searched for: " + searchText);

        Thread.sleep(3000); // Wait for results to load
    }
}
