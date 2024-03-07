package org.chapter5;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import org.chapter5.model.User;

public class Chapter5Section2 {

    public static void main(String[] args) {
        Function<String, Integer> strLength = String::length;
        int length = strLength.apply("hello world");
//        System.out.println(length);

        BiPredicate<String, String> strEquals = String::equals;
//        System.out.println(strEquals.test("hello", "world"));
//        System.out.println(strEquals.test("hello", "hello"));

        List<User> users = Arrays.asList(
            new User(3, "Alice"),
            new User(1, "Charlie"),
            new User(5, "Bob")
        );

        printUserField(users, User::getId);
    }

    public static void printUserField(List<User> users, Function<User, Object> getter) {
        for (User user : users) {
            System.out.println(getter.apply(user));
        }
    }
}
