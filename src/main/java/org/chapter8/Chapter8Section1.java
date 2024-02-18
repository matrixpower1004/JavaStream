package org.chapter8;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.chapter6.model.Order;
import org.chapter6.model.OrderStatus;
import org.chapter8.model.User;

public class Chapter8Section1 {

    public static void main(String[] args) {
        Optional<Integer> max = Stream.of(5, 3, 6, 2, 1)
//            .max((x, y) -> x - y);
            .max(Integer::compareTo);
//        System.out.println(max.get());

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
        User firstUser = users.stream().min(Comparator.comparing(User::getName))
            .get();
//        System.out.println(firstUser);

        long positiveIntegerCount = Stream.of(1, -4, 5, -3, 6)
            .filter(x -> x > 0)
            .count();
//        System.out.println("Positive integers: " + positiveIntegerCount);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        user1.changeCreatedAt(now.minusDays(2));
        user2.changeCreatedAt(now.minusHours(10));
        user3.changeCreatedAt(now.minusHours(1));
        User user4 = User.builder()
            .id(104)
            .name("David")
            .isVerified(false)
            .emailAddress("david@fastcampus.co.kr")
            .createdAt(now.minusHours(27))
            .build();

        // Todo: 최근 24시간 이내에 가입한 유저들 중에서 아직 email 검증이 되지 않은 유저들이 총 몇명이 있는지
        users = Arrays.asList(user1, user2, user3, user4);
        long unVerifiedUsersIn24Hrs = users.stream()
            .filter(user -> user.getCreatedAt().isAfter(now.minusDays(1)))
            .filter(user -> !user.isVerified())
            .count();
//        System.out.println(unVerifiedUsersIn24Hrs);

        Order order1 = Order.builder()
            .id(1001L)
            .amount(BigDecimal.valueOf(2000))
            .status(OrderStatus.CREATED)
            .createdAt(now.minusHours(4))
            .build();

        Order order2 = Order.builder()
            .id(1002L)
            .amount(BigDecimal.valueOf(4000))
            .status(OrderStatus.ERROR)
            .createdByUserId(103)
            .createdAt(now.minusHours(1))
            .build();

        Order order3 = Order.builder()
            .id(1003L)
            .amount(BigDecimal.valueOf(3000))
            .status(OrderStatus.ERROR)
            .createdByUserId(102)
            .createdAt(now.minusHours(36))
            .build();

        Order order4 = Order.builder()
            .id(1004L)
            .amount(BigDecimal.valueOf(7000))
            .status(OrderStatus.PROCESSED)
            .createdByUserId(104)
            .createdAt(now.minusHours(40))
            .build();
        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        // Todo: Find order with highest amount an in ERROR status
        Order errorOrderWithMaxAmount = orders.stream()
            .filter(order -> order.getStatus().equals(OrderStatus.ERROR))
            .max(Comparator.comparing(Order::getAmount))
            .get();
//        System.out.println(errorOrderWithMaxAmount);

        BigDecimal maxErrorAmount = orders.stream()
            .filter(order -> order.getStatus().equals(OrderStatus.ERROR))
            .map(Order::getAmount)
            .max(BigDecimal::compareTo)
            .orElse(BigDecimal.ZERO);
        System.out.println(maxErrorAmount);
    }
}
