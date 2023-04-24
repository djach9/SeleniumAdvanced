package pages.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-title")
    private List<WebElement> productTitles;

    public boolean isPresentInSearchResults(String productName) {
        for (WebElement product : productTitles) {
            if (product.getAttribute("innerText").contains(productName)) {
                return true;
            }
        }
        return false;
    }
}

