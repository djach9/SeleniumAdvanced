package search;

import base.Pages;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class FiltersTest extends Pages {

    @Test
    public void shouldValidateFilters() {
        topBasePage.openAccessoriesCategory();
        int initialProductCount = productGridPage.getProductCount();

        BigDecimal lowerPriceLimit = BigDecimal.valueOf(13.00);
        BigDecimal upperPriceLimit = BigDecimal.valueOf(15.00);

        filtersPage.setPriceOnSlider(lowerPriceLimit, upperPriceLimit);

        List<String> prices = productGridPage.getProductPricesAsString();
        boolean allPricesInRange = prices.stream()
                .map(price -> new BigDecimal(price.replaceAll("[^\\d.]", "")))
                .allMatch(price -> categoryPage.checkIfPriceWithinFiltersRange(price, lowerPriceLimit, upperPriceLimit));

        categoryPage.clearFilters();
        Assertions.assertThat(productGridPage.getProductCount()).isEqualTo(initialProductCount);
    }
}
