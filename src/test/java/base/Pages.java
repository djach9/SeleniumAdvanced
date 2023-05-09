package base;

import org.junit.jupiter.api.BeforeEach;
import pages.basic.HomePage;
import pages.basic.TopBasePage;
import pages.cart.CartPage;
import pages.checkout.CheckoutPage;
import pages.checkout.ConfirmationPage;
import pages.order.OrderDetailsPage;
import pages.order.OrderHistoryPage;
import pages.products.*;
import pages.user.SignInPage;
import pages.user.UserAccountPage;

public class Pages extends BaseTest {

    public HomePage homePage;
    public TopBasePage topBasePage;
    public ProductGridPage productGridPage;
    public SearchResultPage searchResultPage;
    public CategoryPage categoryPage;
    public FiltersPage filtersPage;
    public ProductPage productPage;
    public PopUpProductPage popUpProductPage;
    public SignInPage signInPage;
    public UserAccountPage userAccountPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public ConfirmationPage confirmationPage;
    public OrderHistoryPage orderHistoryPage;
    public OrderDetailsPage orderDetailsPage;

    @BeforeEach
    public void initPages() {
        homePage = new HomePage(driver);
        topBasePage = new TopBasePage(driver);
        productGridPage = new ProductGridPage(driver);
        searchResultPage = new SearchResultPage(driver);
        categoryPage = new CategoryPage(driver);
        filtersPage = new FiltersPage(driver);
        productPage = new ProductPage(driver);
        popUpProductPage = new PopUpProductPage(driver);
        signInPage = new SignInPage(driver);
        userAccountPage = new UserAccountPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        confirmationPage = new ConfirmationPage(driver);
        orderHistoryPage = new OrderHistoryPage(driver);
        orderDetailsPage = new OrderDetailsPage(driver);
    }
}