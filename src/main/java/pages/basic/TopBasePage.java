package pages.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.cart.CartPage;
import pages.products.CategoryPage;
import pages.products.SearchResultPage;
import pages.user.SignInPage;
import pages.user.UserAccountPage;

import java.util.ArrayList;
import java.util.List;

import static configuration.ConfigurationRetriever.getProductData;

public class TopBasePage extends BasePage {

    public TopBasePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ui-autocomplete-input")
    private WebElement searchFieldInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchSubmitButton;

    @FindBy(css = ".ui-autocomplete")
    private List<WebElement> productsFromDropdown;

    @FindBy(xpath = "//a[@data-depth='0']")
    private List<WebElement> productCategories;

    @FindBy(xpath = "//a[@data-depth='1']")
    private List<WebElement> productSubcategories;

    @FindBy(css = "#category-3")
    private WebElement clothesCategorySection;

    @FindBy(css = "#category-6")
    private WebElement accessoriesCategorySection;

    @FindBy(css = "#category-9")
    private WebElement artCategorySection;

    @FindBy(css = ".popover")
    private WebElement popoverMenu;

    @FindBy(css = "span.cart-products-count")
    private WebElement cartProductsCount;

    @FindBy(css = "#_desktop_logo")
    private WebElement shopLogo;

    @FindBy(css = ".shopping-cart")
    private WebElement shoppingCartButton;

    @FindBy(css = "[title='Log in to your customer account']")
    private WebElement signInButton;

    @FindBy(css = ".account .hidden-sm-down")
    private WebElement userAccount;

    public TopBasePage searchForProduct(String productName) {
        sendKeys(searchFieldInput, productName);
        return this;
    }

    public TopBasePage searchFromHummingbirdItem() {
        searchForProduct(getProductData().getProduct1Name());
        return this;
    }

    public List<WebElement> getProductCategories() {
        return productCategories;
    }

    public List<WebElement> getProductSubcategories() {
        return productSubcategories;
    }

    public List <String> getProductNamesFromDropdown() {
        return new ArrayList<>();
    }

    public SearchResultPage clickSearchSubmitButton() {
        click(searchSubmitButton);
        By headerLocator = By.cssSelector(".h2");
        wait.until(ExpectedConditions.textToBe(headerLocator, "SEARCH RESULTS"));
        return new SearchResultPage(driver);
    }

    public String getCategoryName(WebElement category) {
        return category.getText();
    }

    public List<String> getProductCategoryNames() {
        List<String> categoryNames = new ArrayList<>();
        for (WebElement category : getProductCategories()) {
            categoryNames.add(getCategoryName(category));
        }
        return categoryNames;
    }

    public CategoryPage openCategoryByName(String categoryName) {
        for (int i = 0; i < getProductCategories().size(); i++) {
            if (getProductCategories().get(i).getText().equals(categoryName)) {
                click(getProductCategories().get(i));
                break;
            }
        }
        return new CategoryPage(driver);
    }

    public CategoryPage openAccessoriesCategory() {
        click(accessoriesCategorySection);
        return new CategoryPage(driver);
    }

    public CategoryPage openSubcategory(String subcategory) {
        expandSubcategories(subcategory);
        for (int i = 0; i < getProductSubcategories().size(); i++) {
            if (getProductSubcategories().get(i).getText().equals(subcategory)) {
                click(getProductSubcategories().get(i));
                break;
            }
        }
        return new CategoryPage(driver);
    }

    public TopBasePage expandSubcategories(String subcategory) {
        if (subcategory.equalsIgnoreCase("Men") || subcategory.equalsIgnoreCase("Women")) {
            actions.moveToElement(clothesCategorySection).perform();
        } else if (subcategory.equalsIgnoreCase("Stationery") || subcategory.equalsIgnoreCase("Home accessories")) {
            actions.moveToElement(accessoriesCategorySection).perform();
        }
        return this;
    }

    public String getCartProductsCount() {
        return cartProductsCount.getText();
    }

    public HomePage goToHomePage() {
        click(shopLogo);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#carousel")));
        return new HomePage(driver);
    }

    public CartPage goToCart() {
        click(shoppingCartButton);
        return new CartPage(driver);
    }

    public SignInPage clickOnSignInButton() {
        click(signInButton);
        return new SignInPage(driver);
    }

    public UserAccountPage goToUserAccount() {
        click(userAccount);
        return new UserAccountPage(driver);
    }
}