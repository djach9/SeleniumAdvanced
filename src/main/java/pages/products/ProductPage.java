package pages.products;

import models.Order;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;


import java.math.BigDecimal;

import static helpers.Helper.getRandom;

public class ProductPage extends BasePage {

    private PopUpProductPage popUpProductPage = new PopUpProductPage(driver);

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h1")
    private WebElement productName;

    @FindBy(css = "#quantity_wanted")
    private WebElement productQuantityInput;

    @FindBy(css = "[itemprop='price']")
    private WebElement productPrice;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartButton;

    public ProductPage setQuantity(int quantity) {
        clearAndSendKeys(productQuantityInput, String.valueOf(quantity));
        return this;
    }

    public ProductPage setRandomQuantity(int maxValue) {
        setQuantity(getRandom().nextInt(maxValue) + 1);
        return this;
    }

    public PopUpProductPage addProductToCart(Product product) {
        product.setProductName(productName.getText());
        product.setProductPrice(getPrice(productPrice));
        product.setQuantity(Integer.parseInt(productQuantityInput.getAttribute("value")));
        click(addToCartButton);
        popUpProductPage.waitForPopup();
        return new PopUpProductPage(driver);
    }

    public PopUpProductPage addProductToCart(Product product, Order order) {
        product.setProductName(productName.getText());
        product.setProductPrice(getPrice(productPrice));
        product.setQuantity(Integer.parseInt(productQuantityInput.getAttribute("value")));
        product.setProductTotalPrice(product.getProductPrice().multiply(BigDecimal.valueOf(product.getQuantity())));
        order.addProduct(product, product.getQuantity());
        click(addToCartButton);
        popUpProductPage.waitForPopup();
        return new PopUpProductPage(driver);
    }
}