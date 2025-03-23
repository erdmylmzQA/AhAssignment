package com.ah.assignment.utils;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

public class TestUtils {
    private static final Logger log = LoggerFactory.getLogger(TestUtils.class);

    public static int getRandomIndex(List<String> list) {
        return new Random().nextInt(list.size());
    }

    public static int getRandomPageSize() {
        return (new Random().nextInt(5) + 1) * 10;
    }

    public static List<String> extractList(Response response, String path) {
        return response.jsonPath().getList(path);
    }

    public static String getValidOrDefault(String valid, String defaultValue) {
        if (valid == null || valid.isEmpty()) {
            log.info("The value is null or empty. Using default value instead");
            return defaultValue;
        }
        return valid;
    }

    public static String extractFullName(String name) {
        name = name.trim();
        if (name.contains(",")) {
            return name.substring(name.lastIndexOf(",") + 1).trim();
        }
        return name;
    }

    public static String extractString(Response response, String path) {
        return response.jsonPath().getString(path);
    }

    public static int extractInt(Response response, String path) {
        return response.jsonPath().getInt(path);
    }
}
