package pages.checkout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

public class ConfirmationPage extends BasePage {
    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#order-details ul li")
    List<WebElement> orderDetails;

    private String getOrderNumber() {
        return orderDetails.get(0).getAttribute("innerText").replace("\"\"", "");
    }

    public String saveOrderNumber() {
        String[] splitOrderNumberInfo = getOrderNumber().split(" ");
        return splitOrderNumberInfo[2];
    }
}
