package org.chapter6.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMain {

    public static void main(String[] args) {
        User user1 = User.builder()
            .id(101)
            .name("Alice")
            .isVerified(true)
            .emailAddress("alice@fastcampus.co.kr")
            .build();

        User user2 = User.builder()
            .id(102)
            .name("Bob")
            .isVerified(false)
            .emailAddress("bob@fastcampus.co.kr")
            .build();

        User user3 = User.builder()
            .id(103)
            .name("Charlie")
            .isVerified(false)
            .emailAddress("charlie@fastcampus.co.kr")
            .build();

        List<User> users = Arrays.asList(user1, user2, user3);

        List<User> verifiedUsers = users.stream()
            .filter(User::isVerified)
            .collect(Collectors.toList());
//        System.out.println(verifiedUsers);

        List<User> unVerifiedUsers = users.stream()
            .filter(user -> !user.isVerified())
            .collect(Collectors.toList());
//        System.out.println(unVerifiedUsers);

        Order order1 = Order.builder()
            .id(1001)
            .status(OrderStatus.CREATED)
            .build();

    }
}
