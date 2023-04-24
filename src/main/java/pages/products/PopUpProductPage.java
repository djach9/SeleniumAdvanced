package pages.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;
import pages.cart.CartPage;

import java.math.BigDecimal;

public class PopUpProductPage extends BasePage {

    public PopUpProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h6.product-name")
    private WebElement productName;

    @FindBy(css = "p.product-price")
    private WebElement productPrice;

    @FindBy(css = ".product-quantity strong")
    private WebElement productQuantity;

    @FindBy(css = ".subtotal")
    private WebElement totalProductsValue;

    @FindBy(css = "p.cart-products-count")
    private WebElement productCountInfo;

    @FindBy(css = "a.btn.btn-primary")
    private WebElement proceedToCheckoutButton;

    @FindBy(css = ".btn.btn-secondary")
    private WebElement continueShoppingButton;

    public String getProductName() {
        return productName.getText();
    }

    public BigDecimal getProductPrice() {
        return getPrice(productPrice);
    }

    public int getProductQuantity() {
        return Integer.parseInt(productQuantity.getText());
    }

    public BigDecimal getTotalProductPrice() {
        return getPrice(totalProductsValue);
    }

    public String getProductCountInfo() {
        return productCountInfo.getText();
    }

    public void waitForPopup() {
        waitToBeClickable(proceedToCheckoutButton);
    }

    public ProductPage clickContinueShopping() {
        click(continueShoppingButton);
        return new ProductPage(driver);
    }

    public CartPage proceedToCheckout() {
        click(proceedToCheckoutButton);
        return new CartPage(driver);
    }
}