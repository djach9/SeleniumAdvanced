package search;

import base.Pages;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchTest extends Pages {

    @Test
    public void shouldCheckSearchField() {
        String productName = productGridPage.getNameOfRandomProductFromGrid();
        topBasePage.searchForProduct(productName).clickSearchSubmitButton();
        Assertions.assertThat(searchResultPage.getProductNamesFromSearchResults()).contains(productName);
    }


    @Test
    public void shouldCheckSearchDropdown() {
        topBasePage.searchFromHummingbirdItem();
        Assertions.assertThat(topBasePage.getProductNamesFromDropdown()).allMatch(c->c.contains("Hummingbird"));
    }
}