package pages.products;

import helpers.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

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

    public List<WebElement> getProductPrices() {
        return prices;
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

    private ProductPage openProductByName(String productName) {
        products.stream().filter(webelement -> webelement.getText().equals(productName)).toList().get(0).click();
        return new ProductPage(driver);
    }

    public ProductPage openTheBestIsYetPoster() {
        openProductByName(getProductData().getProduct2Name());
        return new ProductPage(driver);
    }

    public ProductPage openRandomProduct() {
        Helper.getRandomElement(products).click();
        return new ProductPage(driver);
    }
}
