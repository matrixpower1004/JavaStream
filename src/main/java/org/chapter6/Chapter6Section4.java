package org.chapter6;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.chapter6.model.Order;
import org.chapter6.model.OrderStatus;
import org.chapter6.model.User;

public class Chapter6Section4 {

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
        // 유저 리스트가 있을 때 이메일이 검증도지 않은 유저만 골라내서 그 유저들의 이메일만 들어있는 String List를 반환해보자.
        List<String> emails = new ArrayList<>();
        for (User user : users) {
            if (!user.isVerified()) {
                String email = user.getEmailAddress();
                emails.add(email);
            }
        }
//        System.out.println(emails);

        // WhatToDo -> 찬찬히 보면 각 과정에서 우리가 무엇을 해야 하는지 역할이 딱딱 나누어져서 코드가 깔끔하게 쓰여진다.
        List<String> emails2 = users.stream()
            .filter(user -> !user.isVerified())
            .map(User::getEmailAddress)
            .collect(Collectors.toList());
//        System.out.println(emails2);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = Order.builder()
            .id(1001)
            .status(OrderStatus.CREATED)
            .createdByUserId(101)
            .createdAt(now.minusHours(4))
            .build();

        Order order2 = Order.builder()
            .id(1001)
            .status(OrderStatus.ERROR)
            .createdByUserId(103)
            .createdAt(now.minusHours(1))
            .build();

        Order order3 = Order.builder()
            .id(1003)
            .status(OrderStatus.PROCESSED)
            .createdByUserId(102)
            .createdAt(now.minusHours(36))
            .build();

        Order order4 = Order.builder()
            .id(1004)
            .status(OrderStatus.ERROR)
            .createdByUserId(104)
            .createdAt(now.minusHours(40))
            .build();

        Order order5 = Order.builder()
            .id(1005)
            .status(OrderStatus.IN_PROGRESS)
            .createdByUserId(105)
            .createdAt(now.minusHours(10))
            .build();

        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // Todo: Find orders in Error status, and extract createdByUserIds as a list
        List<Long> filteredUserIds = orders.stream()
            .filter(order -> order.getStatus().equals(OrderStatus.ERROR))
            .map(Order::getCreatedByUserId)
            .collect(Collectors.toList());
        System.out.println(filteredUserIds);

        // Todo: Find orders in Error stats and created within 24 hours
        // now.isAfter() 또는 isBefore()를 응용해서 만들면 된다.
        List<Order> ordersInErrorStatusIn24hrs = orders.stream()
            .filter(order -> order.getStatus().equals(OrderStatus.ERROR))
            .filter(order -> order.getCreatedAt().isAfter(now.minusHours(24)))
            .collect(Collectors.toList());
        System.out.println(ordersInErrorStatusIn24hrs);
    }
}
