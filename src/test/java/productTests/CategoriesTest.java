package productTests;

import baseTests.Pages;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Stream;

public class CategoriesTest extends Pages {

    @Test
    public void shouldValidateCategories() {
        logger.info("Start categories test");

        List<WebElement> categories = topBasePage.getProductCategories();
        for (int i = 0; i < categories.size(); i++) {
            String chosenCategoryName = topBasePage.getCategoryName(categories.get(i));
            logger.info("Testing category: " + chosenCategoryName);

            topBasePage.openCategory(categories.get(i));
            Assertions.assertAll(
                    () -> Assertions.assertEquals(chosenCategoryName, categoryPage.getHeaderName()),
                    () -> Assertions.assertTrue(categoryPage.isFiltersSectionDisplayed()),
                    () -> Assertions.assertTrue(categoryPage.getDeclaredProductCount()
                            .contains(productGridPage.getProductCountAsString()))
            );
            logger.info("Categories tested- " + chosenCategoryName);
        }
    }

    @ParameterizedTest
    @MethodSource("generateData")
    public void shouldValidateSubcategories(List<String> subcategories) {
        logger.info("Start sub categories test");
        for (String subcategory : subcategories) {
            logger.info("Testing sub category: "+ subcategory);
            topBasePage.openSubcategory(subcategory);
            Assertions.assertAll(
                    () -> Assertions.assertEquals(subcategory, categoryPage.getHeaderName()),
                    () -> Assertions.assertTrue(categoryPage.isFiltersSectionDisplayed()),
                    () -> Assertions.assertTrue(categoryPage.getDeclaredProductCount()
                            .contains(productGridPage.getProductCountAsString()))
            );
            logger.info("Sub categories tested- "+ subcategory);
        }
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(List.of("MEN", "WOMEN", "STATIONERY", "HOME ACCESSORIES"))
        );
    }
}
