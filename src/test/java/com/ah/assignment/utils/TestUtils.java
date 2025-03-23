package com.ah.assignment.utils;

import io.restassured.response.Response;

import java.util.List;
import java.util.Random;

public class TestUtils {
    public static int getRandomIndex(List<String> list) {
        return new Random().nextInt(list.size());
    }

    public static int getRandomPageSize() {
        return (new Random().nextInt(5) + 1) * 10;
    }

    public static List<String> extractList(Response response, String path) {
        return response.jsonPath().getList(path);
    }

}
