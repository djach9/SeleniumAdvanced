package pages.products;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.basic.BasePage;

import java.math.BigDecimal;
import java.util.List;

public class FiltersPage extends BasePage {
    public FiltersPage(WebDriver driver) {
        super(driver);
    }

    private String[] splitPrice;

    @FindBy(css = "[id^='facet_label']")
    private WebElement priceRange;

    @FindBy(css = ".ui-slider-handle")
    private List<WebElement> sliderHandles;

    private String[] getPriceRange() {
        String price = priceRange.getText().replace(getCurrency(), "");
        return splitPrice = price.split(" ");
    }

    public BigDecimal getLowerPriceRange() {
        getPriceRange();
        return new BigDecimal(splitPrice[0]);
    }

    public BigDecimal getUpperPriceRange() {
        getPriceRange();
        return new BigDecimal(splitPrice[2]);
    }

    public FiltersPage setPriceOnSlider(BigDecimal lowerPriceRange, BigDecimal upperPriceRange) {
        waitToBeClickable(priceRange);
        if (lowerPriceRange.compareTo(upperPriceRange) > 0) {
            throw new RuntimeException("Lower price range cannot be greater than upper price range.");
        }
        while (getLowerPriceRange().compareTo(lowerPriceRange) < 0) {
            sliderHandles.get(0).sendKeys(Keys.ARROW_RIGHT);
            wait.until(ExpectedConditions.urlContains("-" + getBigDecimalAsShortString(getLowerPriceRange()) + "-"));
        }
        while (getUpperPriceRange().compareTo(upperPriceRange) > 0) {
            sliderHandles.get(1).sendKeys(Keys.ARROW_LEFT);
            wait.until(ExpectedConditions.urlContains(getBigDecimalAsShortString(getLowerPriceRange()) + "-" + getBigDecimalAsShortString(getUpperPriceRange())));
        }
        return this;
    }
}
