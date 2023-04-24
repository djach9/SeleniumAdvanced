package pages.products;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.basic.BasePage;

import java.math.BigDecimal;
import java.util.List;


public class ProductsCategoriesPage extends BasePage {
    public ProductsCategoriesPage(WebDriver driver) {
        super(driver);
    }

    public ProductGridPage productGridPage = new ProductGridPage(driver);
    public FiltersPage filtersPage = new FiltersPage(driver);

    @FindBy(css = ".h1")
    private WebElement header;

    @FindBy(css = "#search_filters")
    private WebElement filtersSection;

    @FindBy(css = ".total-products p")
    private WebElement searchResultsInfo;

    @FindBy(css = ".close")
    private WebElement clearFilterButton;

    public String getHeaderName() {
        return header.getText();
    }

    public boolean isFiltersSectionDisplayed() {
        return filtersSection.isDisplayed();
    }

    public String getDeclaredProductCount() {
        return searchResultsInfo.getText();
    }

    public void clearFilters() {
        click(clearFilterButton);
        wait.until(ExpectedConditions.invisibilityOf(clearFilterButton));
    }

    public boolean checkIfPriceWithinFiltersRange(WebElement element) {
        if (getBigDecimalAsDouble(productGridPage.getPrice(element)) >= getBigDecimalAsDouble(filtersPage.getLowerPriceRange())) {
            return getBigDecimalAsDouble(productGridPage.getPrice(element)) <= getBigDecimalAsDouble(filtersPage.getUpperPriceRange());
        } else {
            return false;
        }
    }
}