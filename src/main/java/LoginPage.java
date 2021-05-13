import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final By loginLinkLocation = By.cssSelector("[data-test='headerAuth-signInBtn pa-uiLib-headerAuth-authBtn']");
    private final By usernameFieldLocation = By.name("username");
    private final By passwordFieldLocation = By.name("password");
    private final By loginButtonLocation = By.cssSelector(".pa-uiLib-authentication-btn.primary");
    private final By loggedInUserAvatarLocation = By.cssSelector((".pa-uiLib-headerProfileInfo-profileInfo"));
    private final By unsuccessfulLoginMessageContainerLocation = By.cssSelector((".pa-uiLib-authentication-signInForm-container"));

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLoginLink() {
        WebElement loginLink = driver.findElement(loginLinkLocation);
        loginLink.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(usernameFieldLocation));
    }

    public void typeUsername(String username) {
        WebElement usernameField = driver.findElement(usernameFieldLocation);
        usernameField.sendKeys(username);
    }

    public void typePassword(String password) {
        WebElement passwordField = driver.findElement(passwordFieldLocation);
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(loginButtonLocation);
        loginButton.click();
    }

    public boolean isLoggedInSuccessful() {
        WebElement avatar = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(loggedInUserAvatarLocation));
        return avatar.isDisplayed();
    }

    public boolean isLoggedInUnsuccessful() {
        WebElement unsuccessfulLoginMessageContainer = driver.findElement(unsuccessfulLoginMessageContainerLocation);
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(unsuccessfulLoginMessageContainer, "Username or password incorrect"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
