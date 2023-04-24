package pages.basic;

import configuration.ConfigurationRetriever;
import lombok.extern.java.Log;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.cart.CartPage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

import static configuration.ConfigurationRetriever.getProductData;


@Log
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        initDriver(driver);
        PageFactory.initElements(driver, this);
    }

    private void initDriver(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigurationRetriever.getConfig().getWait()));
        actions = new Actions(driver);
    }

    public BigDecimal getPrice(WebElement element) {
        String productPrice = element.getText().replace(getProductData().getCurrency(), "");
        try {
            return new BigDecimal(productPrice);
        } catch (NumberFormatException e) {
            System.out.println("Wrong price format: " + productPrice);
            return BigDecimal.ZERO;
        }
    }


    public void waitToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        waitToBeClickable(element);
        element.click();
    }

    public void sendKeys(WebElement element, String textToType) {
        element.sendKeys(textToType);
    }

    protected void clearAndSendKeys(WebElement element, String textToType) {
        element.clear();
        element.sendKeys(textToType);
    }

    protected String getCurrency() {
        return getProductData().getCurrency();
    }

    public CartPage goToCartUsingUrl() {
        driver.get(ConfigurationRetriever.getConfig().getUrl() + "?controller=cart&action=show");
        return new CartPage(driver);
    }

    public String getBigDecimalAsShortString(BigDecimal value) {
        return value.setScale(0, RoundingMode.DOWN).toString();
    }

    public double getBigDecimalAsDouble(BigDecimal value) {
        return value.doubleValue();
    }
}
