package UsingPlaywright;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class PlaywrightLoginTest {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        // Set headless to true for CI/CD, false to watch it run!
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
    }

    @Test
    public void testSuccessfulLoginWithPlaywright() {

        page.navigate("http://localhost:5173/login");
        page.locator("#username").fill("chinmay123");
        page.locator("#password").fill("password123");
        page.locator("button:has-text('Login')").click();


        page.waitForURL("**/dashboard");
        Assert.assertTrue(page.url().contains("/dashboard"));
    }

    @AfterMethod
    public void teardown() {
        browser.close();
        playwright.close();
    }
}
