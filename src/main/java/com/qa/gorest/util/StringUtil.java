package com.qa.gorest.util;

public class StringUtil {

    public static String getRandomEmailId() {
        return "api" + System.currentTimeMillis() + "@google.com";
    }
}
