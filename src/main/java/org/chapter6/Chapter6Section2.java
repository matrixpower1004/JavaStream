package org.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.chapter6.model.Order;
import org.chapter6.model.OrderStatus;
import org.chapter6.model.User;

public class Chapter6Section2 {

    public static void main(String[] args) {
        Stream<Integer> numberStream = Stream.of(3, -5, 7, 10, -3);
        Stream<Integer> filteredNumberStream = numberStream.filter(x -> x > 0);
        List<Integer> filteredNumbers = filteredNumberStream.collect(Collectors.toList());
//        System.out.println(filteredNumbers);

        List<Integer> newFilteredNumbers = Stream.of(3, -5, 7, 10, -3)
            .filter(x -> x > 0)
            .collect(Collectors.toList());
//        System.out.println(newFilteredNumbers);

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
            .isVerified( true)
            .emailAddress("charlie@fastcampus.co.kr")
            .build();

        List<User> users = Arrays.asList(user1, user2, user3);
        // 이메일 검증된 유저만 골라내기
        List<User> verifiedUsers = users.stream()
            .filter(User::isVerified)
            .collect(Collectors.toList());
//        System.out.println(verifiedUsers);

        // 이메일이 검증되지 않은 유저만 골라내기
        List<User> unverifiedUsers = users.stream()
            .filter(user -> !user.isVerified())
            .collect(Collectors.toList());
//        System.out.println(unverifiedUsers);

        // Order를 이용한 에제
        Order order1 = Order.builder()
            .id(1001)
            .status(OrderStatus.CREATED)
            .createdByUserId(101)
            .build();

        Order order2 = Order.builder()
            .id(1001)
            .status(OrderStatus.ERROR)
            .createdByUserId(103)
            .build();

        Order order3 = Order.builder()
            .id(1001)
            .status(OrderStatus.PROCESSED)
            .createdByUserId(102)
            .build();

        Order order4 = Order.builder()
            .id(1001)
            .status(OrderStatus.ERROR)
            .createdByUserId(104)
            .build();

        Order order5 = Order.builder()
            .id(1001)
            .status(OrderStatus.IN_PROGRESS)
            .createdByUserId(105)
            .build();

        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // Filters orders in ERROR state
        List<Order> errorStatusOrders = orders.stream()
            .filter(order -> order.getStatus().equals(OrderStatus.ERROR))
            .collect(Collectors.toList());
        System.out.println(errorStatusOrders);

    }
}
