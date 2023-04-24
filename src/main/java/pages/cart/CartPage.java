package pages.cart;

import lombok.extern.slf4j.Slf4j;
import models.Order;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.basic.BasePage;
import pages.checkout.CheckoutPage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#cart-subtotal-shipping>.value")
    private WebElement shippingFee;

    @FindBy(css = ".cart-total>.value")
    private WebElement totalValue;

    @FindBy(css = ".remove-from-cart")
    private List<WebElement> removeFromCartButton;

    @FindBy(css = "h1.h1")
    private WebElement shoppingCartLabel;

    @FindBy(css = ".cart-item")
    private List<WebElement> cartLines;

    @FindBy(css = "a.btn.btn-primary")
    private WebElement proceedToCheckoutButton;


    public BigDecimal getShippingFee() {
        return getPrice(shippingFee);
    }

    public BigDecimal getTotalValue() {
        return getPrice(totalValue);
    }

    public CartPage removeProductFromCart(int lineNumber, Order order) {
        int productCount = removeFromCartButton.size();
        waitToBeClickable(removeFromCartButton.get(0));
        click(removeFromCartButton.get(lineNumber - 1));
        order.removeProduct(order.getProducts().get(0));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".remove-from-cart"), productCount - 1));
        return this;
    }

    public List<Product> getCartItems() {
        List<Product> cartItems = new ArrayList<>();
        for (WebElement line : cartLines) {
            cartItems.add(new Product(line.findElement(By.cssSelector("[data-id_customization]")).getText(),
                    getPrice(line.findElement(By.cssSelector(".current-price"))),
                    Integer.parseInt(line.findElement(By.cssSelector("input")).getAttribute("value")),
                    getPrice(line.findElement(By.cssSelector(".product-price strong")))));
        }
        return cartItems;
    }

    public CheckoutPage proceedToCheckout() {
        click(proceedToCheckoutButton);
        return new CheckoutPage(driver);
    }

    public boolean isCartPageOpened() {

        try {
            shoppingCartLabel.isDisplayed();
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}