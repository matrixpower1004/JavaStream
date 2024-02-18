package org.chapter8;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import org.chapter6.model.Order;
import org.chapter6.model.OrderStatus;
import org.chapter8.model.User;

public class Chapter8Section2 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, -4, 2, 7, 9);
        boolean allPositive = numbers.stream()
            .allMatch(number -> number > 0);
//        System.out.println("Are all numbers positive " + allPositive);

        boolean anyNegative = numbers.stream()
            .anyMatch(number -> number < 0);
//        System.out.println("Is any number negative: " + anyNegative);

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
        boolean areAllUserVerified = users.stream()
            .allMatch(User::isVerified); // User들이 전부 검증이 되었는지
//        System.out.println(areAllUserVerified);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
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
            .status(OrderStatus.ERROR )
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

        // Todo: check if any of orders is in ERROR status
        boolean isAnyOrderInErrorsStatus = orders.stream()
            .anyMatch(order -> order.getStatus().equals(OrderStatus.ERROR));
        System.out.println(isAnyOrderInErrorsStatus);
    }
}
