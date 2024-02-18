package org.chapter8;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.chapter8.model.Order;
import org.chapter8.model.OrderLine;
import org.chapter8.model.OrderLineType;
import org.chapter8.model.User;

public class Chapter8Section4 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 4, -2, -5, 3);
        int sum = numbers.stream()
//            .reduce((x, y) -> x + y)
            .reduce(Integer::sum)
            .get();
//        System.out.println("sum: " + sum);

        // min도 reduce를 이용해서 구할 수 있다.
        int min = numbers.stream()
            .reduce((x, y) -> x > y ? x : y).get();
//        System.out.println("min: " + min);

        // 초기값을 제공해 주었기 때문에 항상 값을 리턴해줄 것이므로 .get()을 사용할 필요가 없다.
        int product = numbers.stream()
            .reduce(1, (x, y) -> x * y);
//        System.out.println("product" + product);

        // 3번째 reduce를 이용한다면 map과 reduce를 이용해서 한번에 합쳐서 할 수가 있다.
        List<String> numberStrList = Arrays.asList("3", "2", "5", "-4");
        int sumOfNumberSrtList = numberStrList.stream()
            .map(Integer::parseInt)
            .reduce(0, (x, y) -> x + y);
//        System.out.println("sumOfNumberSrtList" + sumOfNumberSrtList);

        // 일반적으로는 map과 reduce를 따로 쓰는 경우가 더 흔하고 이렇게 combiner는 자주 쓰지 않으니까 이렇게 하는 방법도 있다는 정도만 알아두자
        int sumOfNumberSrtList2 = numberStrList.stream()
            .reduce(
                0, (number, str) -> number + Integer.parseInt(str), (num1, num2) -> num1 + num2);
//        System.out.println(sumOfNumberSrtList2);

        User user1 = User.builder()
            .id(101)
            .name("Alice")
            .friendUserIds(Arrays.asList(201, 202, 203, 204))
            .build();

        User user2 = User.builder()
            .id(102)
            .name("Bob")
            .friendUserIds(Arrays.asList(204, 205, 206))
            .build();

        User user3 = User.builder()
            .id(103)
            .name("Charlie")
            .friendUserIds(Arrays.asList(204, 205, 207))
            .build();
        List<User> users = Arrays.asList(user1, user2, user3);

        // 이들의 친구들의 수 총 합이 얼마나 되는지 reduce를 통해서 알아보자
        Integer sumOfNumberOfFriends = users.stream()
            .map(User::getFriendUserIds)
            .map(List::size)
            .reduce(0, (x, y) -> x + y);
//        System.out.println(sumOfNumberOfFriends);

        Order order1 = Order.builder()
            .id(1001)
            .orderLines(
                Arrays.asList(
                    OrderLine.builder().id(10001).type(OrderLineType.PURCHASE)
                        .amount(BigDecimal.valueOf(1000)).build(),
                    OrderLine.builder().id(10002).type((OrderLineType.PURCHASE))
                        .amount(BigDecimal.valueOf(2000)).build()
                )).build();

        Order order2 = Order.builder()
            .id(1002)
            .orderLines(
                Arrays.asList(
                    OrderLine.builder().id(10003).type(OrderLineType.PURCHASE)
                        .amount(BigDecimal.valueOf(2000)).build(),
                    OrderLine.builder().id(10004).type((OrderLineType.DISCOUNT))
                        .amount(BigDecimal.valueOf(3000)).build()
                )).build();

        Order order3 = Order.builder()
            .id(1003)
            .orderLines(
                Arrays.asList(
                    OrderLine.builder().id(10005).type(OrderLineType.PURCHASE)
                        .amount(BigDecimal.valueOf(1000)).build(),
                    OrderLine.builder().id(10005).type(OrderLineType.PURCHASE)
                        .amount(BigDecimal.valueOf(2000)).build()
                )).build();
        List<Order> orders = Arrays.asList(order1, order2, order3);

        // Todo: find the sum of amounts
        BigDecimal sumOfAmounts = orders.stream()
            .map(Order::getOrderLines)
            .flatMap(List::stream)
            .map(OrderLine::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println(sumOfAmounts);
    }
}
