package config;

import io.restassured.RestAssured;

public class ConfigManager {

    public static void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}