package searchTests;

import baseTests.Pages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;

public class FiltersTest extends Pages {

    @Test
    public void shouldValidateFilters() {
        logger.info("Start filters test");
        List<WebElement> elements = productGridPage.getProductPrices();
        topBasePage.openAccessoriesCategory();
        int initialProductCount = productGridPage.getProductCount();

        logger.info("Set price range on slider");
        filtersPage.setPriceOnSlider(BigDecimal.valueOf(13.00), BigDecimal.valueOf(15.00));

        for (WebElement element : elements) {
            logger.info("Checking price within filters range");
            Assertions.assertTrue(categoryPage.checkIfPriceWithinFiltersRange(element));
        }

        logger.info("Clear filters");
        categoryPage.clearFilters();
        Assertions.assertEquals(productGridPage.getProductCount(), initialProductCount);
        logger.info("Filters test completed");
    }
}
