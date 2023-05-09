package pages.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-title")
    private List<WebElement> productTitles;

    public List<String> getProductNamesFromSearchResults() {
        List<String> productNames = new ArrayList<>();
        for (WebElement product : productTitles) {
            productNames.add(product.getAttribute("innerText"));
        }
        return productNames;
    }

}

