package pages.order;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import java.util.List;

public class OrderHistoryPage extends BasePage {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody tr")
    private List<WebElement> orderRows;

    private WebElement getCorrectRow(String orderNumber) {
        for (WebElement row : orderRows) {
            if (row.getAttribute("textContent").contains(orderNumber)) {
                return row;
            }
        }
        return null;
    }

    public OrderDetailsPage openOrderDetails(String orderNumber) {
        WebElement orderLine = getCorrectRow(orderNumber);
        click(orderLine.findElement(By.cssSelector("a[data-link-action='view-order-details']")));
        return new OrderDetailsPage(driver);
    }
}
