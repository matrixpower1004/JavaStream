package org.chapter6;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.chapter6.model.Order;
import org.chapter6.model.OrderStatus;
import org.chapter6.model.User;

public class Chapter6Section5 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, -5, 7, 4);
        List<Integer> sortedNumbers = numbers.stream()
            .sorted()
            .collect(Collectors.toList());
//        System.out.println(sortedNumbers);

        User user1 = User.builder()
            .id(101)
            .name("Paul")
            .isVerified(true)
            .emailAddress("alice@fastcampus.co.kr")
            .build();

        User user2 = User.builder()
            .id(102)
            .name("David")
            .isVerified(false)
            .emailAddress("bob@fastcampus.co.kr")
            .build();

        User user3 = User.builder()
            .id(103)
            .name("John")
            .isVerified(false)
            .emailAddress("charlie@fastcampus.co.kr")
            .build();

        List<User> users = Arrays.asList(user1, user2, user3);

        List<User> sortedUsers = users.stream()
            .sorted(Comparator.comparing(User::getName))
            .collect(Collectors.toList());
//        System.out.println(sortedUsers);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = Order.builder()
            .id(1001)
            .status(OrderStatus.CREATED)
            .createdByUserId(101)
            .createdAt(now.minusHours(4))
            .build();

        Order order2 = Order.builder()
            .id(1002)
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

        //Todo: Sort the orders based on createdAT
        List<Order> sortedOrders = orders.stream()
            .sorted(Comparator.comparing(Order::getCreatedAt))
            .collect(Collectors.toList());
        System.out.println(sortedOrders);
    }
}
