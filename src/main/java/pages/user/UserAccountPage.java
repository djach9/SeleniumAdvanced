package pages.user;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;
import pages.order.OrderHistoryPage;

public class UserAccountPage extends BasePage {
    public UserAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#history-link .material-icons")
    WebElement orderHistoryButton;

    public OrderHistoryPage openOrderHistoryPage() {
        click(orderHistoryButton);
        return new OrderHistoryPage(driver);
    }
}
