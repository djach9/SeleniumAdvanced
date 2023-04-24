package pages.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.math.BigDecimal;

public class OrderDetailsPage extends BasePage {

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-history td:nth-of-type(1)")
    private WebElement date;

    @FindBy(css = "#order-history td:nth-of-type(2)")
    private WebElement orderStatus;

     @FindBy(css = "td.text-xs-right:last-child")
     private WebElement totalOrderValue;

    @FindBy(css = "#delivery-address")
    private WebElement deliveryAddress;

    @FindBy(css = "#invoice-address")
    private WebElement invoiceAddress;

    public String getDate() {
        return date.getText();
    }

    public String getOrderStatus() {
        return orderStatus.getText();
    }

    public BigDecimal getTotalOrderValue() {
        return getPrice(totalOrderValue);
    }

    public String getDeliveryAddress() {
        return deliveryAddress.getText();
    }

    public String getInvoiceAddress() {
        return invoiceAddress.getText();
    }
}
