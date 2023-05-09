package pages.products;

import helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static configuration.ConfigurationRetriever.getProductData;

public class ProductGridPage extends BasePage {

    public ProductGridPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-title")
    private List<WebElement> products;

    @FindBy(css = "[aria-label='Price']")
    private List<WebElement> prices;

    public List<String> getProductPricesAsString() {
        List<String> pricesAsString = new ArrayList<>();
        for (WebElement priceElement : prices) {
            pricesAsString.add(priceElement.getText());
        }
        return pricesAsString;
    }

    public String getNameOfRandomProductFromGrid() {
        return Helper.getRandomElement(products).getText();
    }

    public int getProductCount() {
        return products.size();
    }

    public String getProductCountAsString() {
        return String.valueOf(products.size());
    }

    public ProductPage openProductByName(String productName) {
        if (products.isEmpty()) {
            throw new RuntimeException("No products found on the page");
        }

        WebElement productElement = products.stream()
                .filter(webelement -> webelement.getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found: " + productName));

        productElement.click();
        return new ProductPage(driver);
    }

    public ProductPage openRandomProduct() {
        Helper.getRandomElement(products).click();
        return new ProductPage(driver);
    }
}
