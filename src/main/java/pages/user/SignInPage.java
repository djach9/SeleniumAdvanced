package pages.user;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.basic.BasePage;

import static configuration.ConfigurationRetriever.getUserData;

@Slf4j
public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".col-md-6 [name='email']")
    private WebElement emailInput;

    @FindBy(css = ".js-visible-password")
    private WebElement passwordInput;

    @FindBy(css = "#submit-login")
    private WebElement signInButton;

    public UserAccountPage logInAsRegisteredUser() {
        sendKeys(emailInput, getUserData().getEmail());
        sendKeys(passwordInput, getUserData().getPassword());
        click(signInButton);
        return new UserAccountPage(driver);
    }
}
