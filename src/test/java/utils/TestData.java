package utils;

import java.util.UUID;

public class TestData {

    public static String randomUsername() {
        return "user_" + UUID.randomUUID().toString().substring(0, 5);
    }

    public static String email(String username) {
        return username + "@example.com";
    }
}