package pages.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.basic.BasePage;

import java.util.List;

import static configuration.ConfigurationRetriever.getUserData;

public class CheckoutPage extends BasePage {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[data-link-action='different-invoice-address']")
    private WebElement differentInvoiceAddressLink;

    @FindBy(css = "[name='address1']")
    private WebElement addressInput;

    @FindBy(css = "[name='postcode']")
    private WebElement postCodeInput;

    @FindBy(css = "[name='city']")
    private WebElement cityInput;

    @FindBy(css = "[name='id_state']")
    private WebElement stateSelect;


    @FindBy(css = ".form-footer .continue")
    private WebElement invoiceContinueButton;

    @FindBy(css = "#delivery_option_1")
    private WebElement shippingMethod;

    @FindBy(css = ".delivery-options-list .continue")
    private WebElement deliveryContinueButton;

    @FindBy(css = "input[data-module-name='ps_checkpayment']")
    private WebElement payByCheckCheckbox;

    @FindBy(css = ".custom-checkbox")
    private WebElement termsOfServiceCheckbox;

    @FindBy(css = "#payment-confirmation .btn")
    private WebElement paymentConfirmationButton;

    @FindBy(css = ".delete-address")
    private List<WebElement> deleteAddressButtons;
    public CheckoutPage  selectState(String state) {
        new Select(stateSelect).selectByVisibleText(state);
        return this;
    }

    public CheckoutPage clickChangeInvoiceAddress() {
        checkAndDeleteUnnecessaryAddress();
        click(differentInvoiceAddressLink);
        return this;
    }

    public CheckoutPage fillInvoiceAddressForm() {
        sendKeys(addressInput, getUserData().getInvoiceAddress());
        sendKeys(postCodeInput, getUserData().getInvoiceZipCode());
        sendKeys(cityInput, getUserData().getInvoiceCity());
        new Select(stateSelect).selectByVisibleText(getUserData().getState());
        click(invoiceContinueButton);
        return this;
    }

    public CheckoutPage continueWithSelectedShippingMethod() {
        click(deliveryContinueButton);
        return this;
    }

    public CheckoutPage selectPayment() {
        payByCheckCheckbox.click();
        return this;
    }

    public CheckoutPage acceptTermsOfService() {
        click(termsOfServiceCheckbox);
        return this;
    }

    public ConfirmationPage submitOrder() {
        click(paymentConfirmationButton);
        return new ConfirmationPage(driver);
    }

    private void checkAndDeleteUnnecessaryAddress() {
        if (deleteAddressButtons.size() > 1) {
            for (int i = 1; i < deleteAddressButtons.size(); i++) {
                click(deleteAddressButtons.get(i));
            }
        }
    }
}