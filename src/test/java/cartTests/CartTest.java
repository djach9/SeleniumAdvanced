package cartTests;

import baseTests.Pages;
import models.Order;
import models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest extends Pages {

    @Test
    public void shouldCheckAddingProductsToCart() {
        logger.info("Starting testing adding products to cart");
        Product product = new Product();
        topBasePage.openArtCategory();
        logger.info("Navigated to 'Art' category.");
        productGridPage.openTheBestIsYetPoster();
        logger.info("Opened 'The Best is Yet' poster product.");
        productPage.setQuantity(3).addProductToCart(product);
        logger.info("Added 3 products to cart.");

        Assertions.assertAll(
                () -> assertEquals(popUpProductPage.getProductName(), product.getProductName()),
                () -> assertEquals(popUpProductPage.getProductPrice(), product.getProductPrice()),
                () -> assertEquals(popUpProductPage.getProductQuantity(), product.getQuantity()),
                () -> assertEquals(popUpProductPage.getProductCountInfo(), String.format("There are %s items in your cart.", product.getQuantity())),
                () -> assertEquals(popUpProductPage.getTotalProductPrice(), product.getProductPrice()
                        .multiply(BigDecimal.valueOf(product.getQuantity())))
        );
        popUpProductPage.clickContinueShopping();
        logger.info("Clicked 'Continue shopping' button.");
        assertEquals(topBasePage.getCartProductsCount(), String.format("(%s)", product.getQuantity()));
        logger.info("Cart contains {} product(s).", product.getQuantity());

    }

    @Test
    public void shouldCheckShoppingCart() {
        Order order = new Order();
        logger.info("Adding products to cart...");

        for (int i = 0; i < 10; i++) {
            topBasePage.goToHomePage();
            productGridPage.openRandomProduct();
            logger.info("Adding product");
            productPage.setRandomQuantity(5);
            productPage.addProductToCart(new Product(), order);
            popUpProductPage.clickContinueShopping();
            logger.info("Added product to cart. Product count: {}", order.getProducts().size());
        }
        homePage.goToCartUsingUrl();
        logger.info("Checking cart items...");
        assertThat(order.getProducts()).isEqualTo(cartPage.getCartItems()).usingRecursiveComparison();
        logger.info("Cart items match expected.");


    }

    @Test
    public void shouldCheckRemovingProductsFromCart() {
        Order order = new Order();
        for (int i = 0; i < 2; i++) {
            topBasePage.goToHomePage();
            productGridPage.openRandomProduct();
            logger.info("Adding product to cart");
            productPage.addProductToCart(new Product(), order);
            popUpProductPage.clickContinueShopping();
        }
        topBasePage.goToCart();
        logger.info("Checking total value.Total value is " + cartPage.getTotalValue());
        assertEquals(order.getPriceWithShippingFee(), cartPage.getTotalValue());
        cartPage.removeProductFromCart(1, order);
        logger.info("Checking total value after removing the first product.Value: " + cartPage.getTotalValue());

        assertEquals(order.getTotalPrice()
                .add(new BigDecimal(String.valueOf(cartPage.getShippingFee()))), cartPage.getTotalValue());
        cartPage.removeProductFromCart(1, order);
        Assertions.assertTrue(cartPage.isCartPageOpened());
        logger.info("Empty cart was displayed");

    }
}
