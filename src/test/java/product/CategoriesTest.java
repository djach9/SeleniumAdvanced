package product;

import base.Pages;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

public class CategoriesTest extends Pages {

    @Test
    public void shouldValidateCategories() {
        SoftAssertions softly = new SoftAssertions();
        List<String> categoryNames = topBasePage.getProductCategoryNames();
        for (String categoryName : categoryNames) {
            topBasePage.openCategoryByName(categoryName);
            softly.assertThat(categoryPage.getHeaderName()).isEqualTo(categoryName);
            softly.assertThat(categoryPage.isFiltersSectionDisplayed()).isTrue();
            softly.assertThat(categoryPage.getDeclaredProductCount())
                    .as("Product count does not match expected value")
                    .contains(productGridPage.getProductCountAsString());
        }
        softly.assertAll();
    }


    @ParameterizedTest
    @MethodSource("generateData")
    public void shouldValidateSubcategories(List<String> subcategories) {
        for (String subcategory : subcategories) {
            topBasePage.openSubcategory(subcategory);
            Assertions.assertAll(
                    () -> Assertions.assertEquals(subcategory, categoryPage.getHeaderName()),
                    () -> Assertions.assertTrue(categoryPage.isFiltersSectionDisplayed()),
                    () -> Assertions.assertTrue(categoryPage.getDeclaredProductCount()
                            .contains(productGridPage.getProductCountAsString()))
            );
        }
    }

    private static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(List.of("MEN", "WOMEN", "STATIONERY", "HOME ACCESSORIES"))
        );
    }
}
