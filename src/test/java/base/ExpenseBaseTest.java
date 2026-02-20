package base;

import config.ConfigManager;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class ExpenseBaseTest {
    @BeforeClass
    public void setup() {
        ConfigManager.setup();
        RestAssured.baseURI = "http://localhost:8082";
    }
}
