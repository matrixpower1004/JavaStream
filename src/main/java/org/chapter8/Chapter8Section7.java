package org.chapter8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.chapter8.model.Order;
import org.chapter8.model.OrderStatus;

public class Chapter8Section7 {

    public static void main(String[] args) {

        // 1의 자리수 별로 묶어서 같은 1의 자리수끼리 리스트가 되도록
        // key는 1의 자리수가 될것이고 value는 같은 1의 자리수를 가지는 숫자들의 리스트가 된다.
        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        Map<Integer, List<Integer>> unitDigitMap = numbers.stream()
            .collect(Collectors.groupingBy(number -> number % 10));
//        System.out.println(unitDigitMap);

        // groupingBy를 할 때 두번째 파라미터로 컬렉터를 넘길수 있다. 이번에는 List 대신 Set으로 받아보자
        Map<Integer, Set<Integer>> unitDigitSet = numbers.stream()
            .collect(Collectors.groupingBy(number -> number % 10, Collectors.toSet()));
//        System.out.println(unitDigitSet);

        // 이번에는 mapping을 이용해서 한 단계 더 나가보자. 키는 Integer, unitDigit는 이것입니다라는 String 값을 value로 가지도록 만들어보자.
        Map<Integer, List<String>> unitDigitStrMap = numbers.stream()
            .collect(Collectors.groupingBy(number -> number % 10,
                Collectors.mapping(number -> "unit digit is" + number, Collectors.toList())));
        System.out.println(unitDigitStrMap);


        Order order1 = Order.builder()
            .id(1001L)
            .amount(BigDecimal.valueOf(2000))
            .status(OrderStatus.CREATED)
            .build();

        Order order2 = Order.builder()
            .id(1002L)
            .amount(BigDecimal.valueOf(4000))
            .status(OrderStatus.ERROR)
            .createdByUserId(103)
            .build();

        Order order3 = Order.builder()
            .id(1003L)
            .amount(BigDecimal.valueOf(3000))
            .status(OrderStatus.ERROR)
            .build();

        Order order4 = Order.builder()
            .id(1004L)
            .amount(BigDecimal.valueOf(7000))
            .status(OrderStatus.PROCESSED)
            .build();
        List<Order> orders = Arrays.asList(order1, order2, order3, order4);

        // Todo: create a map from order status to the list of corresponding orders
        Map<OrderStatus, List<Order>> orderStausMap = orders.stream()
            .collect(Collectors.groupingBy(Order::getStatus));
//        System.out.println(orderStausMap);

        // Stream을 이용하면 이렇게 간단하게 grouping과 매핑이 간단해진다.
        Map<OrderStatus, BigDecimal> orderStatusToSumOfAmountMap = orders.stream()
            .collect(Collectors.groupingBy(
                Order::getStatus,
                Collectors.mapping(
                    Order::getAmount, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
            ));
        System.out.println(orderStatusToSumOfAmountMap);
    }
}
