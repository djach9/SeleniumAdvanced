package cart;

import base.Pages;
import models.Order;
import models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static configuration.ConfigurationRetriever.getProductData;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest extends Pages {

    @Test
    public void shouldCheckAddingProductsToCart() {
        Product product = new Product();
        topBasePage.openCategoryByName(getProductData().getCategory());
        productGridPage.openProductByName(getProductData().getProduct2Name());
        productPage.setQuantity(3).addProductToCart(product);

        Assertions.assertAll(
                () -> assertEquals(popUpProductPage.getProductName(), product.getProductName()),
                () -> assertEquals(popUpProductPage.getProductPrice(), product.getProductPrice()),
                () -> assertEquals(popUpProductPage.getProductQuantity(), product.getQuantity()),
                () -> assertEquals(popUpProductPage.getProductCountInfo(), String.format("There are %s items in your cart.", product.getQuantity())),
                () -> assertEquals(popUpProductPage.getTotalProductPrice(), product.getProductPrice()
                        .multiply(BigDecimal.valueOf(product.getQuantity())))
        );
        popUpProductPage.clickContinueShopping();
        assertEquals(topBasePage.getCartProductsCount(), String.format("(%s)", product.getQuantity()));

    }

    @Test
    public void shouldCheckShoppingCart() {
        Order order = new Order();
        logger.info("Adding products to cart...");

        for (int i = 0; i < 10; i++) {
            topBasePage.goToHomePage();
            productGridPage.openRandomProduct();
            productPage.setRandomQuantity(5);
            productPage.addProductToCart(new Product(), order);
            popUpProductPage.clickContinueShopping();
        }
        homePage.goToCartUsingUrl();
        assertThat(order.getProducts()).isEqualTo(cartPage.getCartItems()).usingRecursiveComparison();


    }

    @Test
    public void shouldCheckRemovingProductsFromCart() {
        Order order = new Order();
        for (int i = 0; i < 2; i++) {
            topBasePage.goToHomePage();
            productGridPage.openRandomProduct();
            productPage.addProductToCart(new Product(), order);
            popUpProductPage.clickContinueShopping();
        }
        topBasePage.goToCart();
        assertEquals(order.getPriceWithShippingFee(), cartPage.getTotalValue());
        cartPage.removeProductFromCart(1, order);

        assertEquals(order.getTotalPrice()
                .add(new BigDecimal(String.valueOf(cartPage.getShippingFee()))), cartPage.getTotalValue());
        cartPage.removeProductFromCart(1, order);
        Assertions.assertTrue(cartPage.isCartPageOpened());

    }
}
