import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Arrays;

public class Login {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/artyomtonoyan/Documents/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://picsartstage2.com");

        WebElement logInLink = driver.findElementByCssSelector("[data-test='headerAuth-signInBtn pa-uiLib-headerAuth-authBtn']");
        logInLink.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("pa-uiLib-authentication-switchAction")));
//        Thread.sleep(2500);
        WebElement logInButton = driver.findElement(By.cssSelector(".pa-uiLib-authentication-btn.primary"));
        if (isDisabled(logInButton)) {
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("testchallenge2");
            WebElement passwordField = driver.findElement(By.name("password"));
            passwordField.sendKeys("testchallenge");
        } else {
            throw new Exception("The Login button is disabled");
        }
        if (!isDisabled(logInButton)) {
            logInButton.click();
        } else {
            throw new Exception("Login button is not enabled");
        }
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("pa-uiLib-headerProfileInfo-profileInfo")));
            System.out.println("Login Successful");
        } catch (Exception e) {
            System.out.println("Login Unsuccessful");
        }
    }

    public static boolean isDisabled(WebElement webElement) {
        ArrayList<String> classesArrayList = new ArrayList<>(Arrays.asList(webElement.getAttribute("class").split(" ")));
        for (String s : classesArrayList) {
            if (s.equals("pa-uiLib-authentication-button-disabled")) {
                return true;
            }
        }
        return false;
    }
}