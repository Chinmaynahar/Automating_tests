package UsingSelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class LoginUITest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:5173/login");

        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.loginAs("chinmay123", "password123");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        Assert.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("/dashboard"), "Login failed, not on dashboard.");
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        loginPage.loginAs("chinmay123", "wrongpassword");

        String error = loginPage.getErrorMessage();
        Assert.assertEquals(error, "Invalid username or password");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Always quit the driver to kill the browser process
        }
    }
}
