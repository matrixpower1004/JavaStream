package org.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMain {

    public static void main(String[] args) {
        Map<List<Long>, List<String>> testMap = new HashMap<>();

        List<Long> longNumbers = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        List<String> strList = Arrays.asList("1x", "2x", "3x", "4x", "5x");
        testMap.put(longNumbers, strList);
        for (int i = 0; i < testMap.size(); i++) {
            List<String> strings = testMap.get(longNumbers.get(i));
        }
    }

}
