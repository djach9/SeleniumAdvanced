package searchTests;

import baseTests.Pages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchTest extends Pages {

    @Test
    public void shouldCheckSearchField() {
        logger.info("Start search product title test");
        String productName = productGridPage.getNameOfRandomProductFromGrid();
        topBasePage.searchForProduct(productName).clickSearchSubmitButton();
        Assertions.assertTrue(searchResultPage.isPresentInSearchResults(productName));
        logger.info("Finished search product title test");

    }


    @Test
    public void shouldCheckSearchDropdown() {
        logger.info("Start dropdown search test");
        topBasePage.searchFromHummingbirdItem();
        Assertions.assertTrue(topBasePage.doProductNamesInDropdownMatch());
        logger.info("Finished dropdown search test");

    }
}