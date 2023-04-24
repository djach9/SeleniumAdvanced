package checkoutTests;

import baseTests.Pages;
import helpers.Helper;
import models.Order;
import models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static configuration.ConfigurationRetriever.getUserData;

public class CheckoutTest extends Pages {

    @Test
    public void shouldCheckOrderProcess() {
        logger.info("Start order process test");

        Order order = new Order();
        topBasePage.clickOnSignInButton();
        logger.info("User clicked on Sign In button");
        signInPage.logInAsRegisteredUser();
        logger.info("User logged in as registered user");
        topBasePage.goToHomePage();
        logger.info("User navigated to home page");
        productGridPage.openTheBestIsYetPoster();
        logger.info("User opened The Best Is Yet Poster product page");
        productPage.addProductToCart(new Product(), order);
        logger.info("User added product to cart");
        popUpProductPage.proceedToCheckout();
        logger.info("User proceeded to checkout from popup window");
        cartPage.proceedToCheckout();
        logger.info("User proceeded to checkout from cart page");
        checkoutPage.clickChangeInvoiceAddress()
                .fillInvoiceAddressForm()
                .continueWithSelectedShippingMethod()
                .selectPayment()
                .acceptTermsOfService()
                .submitOrder();
        String orderNumber = confirmationPage.saveOrderNumber();
        logger.info("User submitted the order");
        topBasePage.goToUserAccount();
        logger.info("User navigated to user account page");
        userAccountPage.openOrderHistoryPage();
        logger.info("User opened order history page");
        orderHistoryPage.openOrderDetails(orderNumber);

        Assertions.assertAll(
                () -> {
                    Assertions.assertEquals(Helper.getTodaysDate(), orderDetailsPage.getDate());
                    logger.info("Today's date matches order details page date");
                },
                () -> {
                    Assertions.assertEquals("Awaiting check payment", orderDetailsPage.getOrderStatus());
                    logger.info("Order status is 'Awaiting check payment'");
                },
                () -> {
                    Assertions.assertEquals(order.getTotalPrice(), orderDetailsPage.getTotalOrderValue());
                    logger.info("Total price matches order details page total order value");
                },
                () -> {
                    Assertions.assertTrue(orderDetailsPage.getDeliveryAddress().contains(getUserData().getFirstName()));
                    Assertions.assertTrue(orderDetailsPage.getDeliveryAddress().contains(getUserData().getLastName()));
                    Assertions.assertTrue(orderDetailsPage.getDeliveryAddress().contains(getUserData().getAddress()));
                    Assertions.assertTrue(orderDetailsPage.getDeliveryAddress().contains(getUserData().getZipCode()));
                    Assertions.assertTrue(orderDetailsPage.getDeliveryAddress().contains(getUserData().getCity()));
                    Assertions.assertTrue(orderDetailsPage.getDeliveryAddress().contains(getUserData().getState()));
                    logger.info("Delivery address details match user data");
                },
                () -> {
                    Assertions.assertTrue(orderDetailsPage.getInvoiceAddress().contains(getUserData().getFirstName()));
                    Assertions.assertTrue(orderDetailsPage.getInvoiceAddress().contains(getUserData().getLastName()));
                    Assertions.assertTrue(orderDetailsPage.getInvoiceAddress().contains(getUserData().getInvoiceAddress()));
                    Assertions.assertTrue(orderDetailsPage.getInvoiceAddress().contains(getUserData().getInvoiceZipCode()));
                    Assertions.assertTrue(orderDetailsPage.getInvoiceAddress().contains(getUserData().getInvoiceCity()));
                    logger.info("Invoice address details match user data");
                }
        );
        logger.info("Order process test finished");

    }
}
