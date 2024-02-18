package org.chapter6;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.chapter6.model.Order;
import org.chapter6.model.OrderStatus;

public class Chapter6Section6 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, -5, 4, -5, 2, 3);
        List<Integer> distinctNumbers = numbers.stream()
            .distinct()
            .collect(Collectors.toList());
//        System.out.println(distinctNumbers);

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
            .createdByUserId(101)
            .createdAt(now.minusHours(10))
            .build();

        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);
        // Todo: create a sorted list of unique CreatedByUsrIds from the Orders
        List<Long> userIds = orders.stream()
            .map(Order::getCreatedByUserId)
            .distinct()
            .sorted()
            .collect(Collectors.toList());
        System.out.println(userIds);
    }
}
