import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Login {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/artyomtonoyan/Documents/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://picsartstage2.com");
    }

    @AfterMethod
    public void quit(){
        //In order to not quit fast
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    @Test
    public void testMethodPositiveCase() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginLink();
        loginPage.typeUsername("testexplore");
        loginPage.typePassword("testexplore");
        //Just for Recaptcha
        Thread.sleep(3000);
        loginPage.clickLoginButton();
        assertTrue(driver.getCurrentUrl().contains("/create"), "User was not logged in");
    }

    @Test
    public void testMethodNegativeCase() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginLink();
        loginPage.typeUsername("testexplore");
        loginPage.typePassword("testexplore");
        //Just for Recaptcha
        Thread.sleep(3000);
        loginPage.clickLoginButton();
//        assertFalse(driver.getCurrentUrl().contains("/create"), "User logged in successfully");
        assertTrue(loginPage.isLoggedInUnsuccessful(), "User logged in successfully");
    }
}