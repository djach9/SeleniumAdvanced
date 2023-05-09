package checkout;

import base.Pages;
import helpers.Helper;
import models.Order;
import models.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static configuration.ConfigurationRetriever.getUserData;
import static configuration.ConfigurationRetriever.getProductData;

public class CheckoutTest extends Pages {

    @Test
    public void shouldCheckOrderProcess() {
        Order order = new Order();
        Product product = new Product();
        topBasePage.clickOnSignInButton();
        signInPage.logInAsRegisteredUser();
        topBasePage.goToHomePage();
        productGridPage.openProductByName(getProductData().getProduct2Name());

        productPage.addProductToCart(new Product(), order);
        popUpProductPage.proceedToCheckout();
        cartPage.proceedToCheckout();
        checkoutPage.clickChangeInvoiceAddress()
                .fillInvoiceAddressForm()
                .continueWithSelectedShippingMethod()
                .selectPayment()
                .acceptTermsOfService()
                .submitOrder();
        String orderNumber = confirmationPage.saveOrderNumber();
        topBasePage.goToUserAccount();
        userAccountPage.openOrderHistoryPage();
        orderHistoryPage.openOrderDetails(orderNumber);

        Assertions.assertThat(Helper.getTodaysDate())
                .as("The order date should be today's date")
                .isEqualTo(orderDetailsPage.getDate());

        Assertions.assertThat(orderDetailsPage.getOrderStatus())
                .as("The order status should be 'Awaiting check payment'")
                .isEqualTo("Awaiting check payment");

        Assertions.assertThat(order.getTotalPrice())
                .as("The total price of the order should be correct")
                .isEqualTo(orderDetailsPage.getTotalOrderValue());

        Assertions.assertThat(orderDetailsPage.getDeliveryAddress())
                .as("The delivery address should match the user's data")
                .contains(getUserData().getFirstName())
                .contains(getUserData().getLastName())
                .contains(getUserData().getAddress())
                .contains(getUserData().getZipCode())
                .contains(getUserData().getCity())
                .contains(getUserData().getState());

        Assertions.assertThat(orderDetailsPage.getInvoiceAddress())
                .as("The invoice address should match the user's data")
                .contains(getUserData().getFirstName())
                .contains(getUserData().getLastName())
                .contains(getUserData().getInvoiceAddress())
                .contains(getUserData().getInvoiceZipCode())
                .contains(getUserData().getInvoiceCity());
    }
}
