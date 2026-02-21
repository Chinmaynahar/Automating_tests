package UsingSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // 1. Exact Locators from your React component
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");

    // Your button uses a CustomButton component inside a div.
    // The safest way is to target the text "Login" or submit the form directly.
    private final By loginForm = By.id("login-form");

    // Your error message conditionally renders with these Tailwind classes
    private final By errorMessage = By.cssSelector("p.text-red-400");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void loginAs(String username, String password) {
        Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput))).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);

        // Since your React button triggers a form submit via DOM events,
        // calling .submit() on the form is the most robust Selenium approach here.
        driver.findElement(loginForm).submit();
    }

    public String getErrorMessage() {
        return Objects.requireNonNull(wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage))).getText();
    }
}