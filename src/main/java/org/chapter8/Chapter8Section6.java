package org.chapter8;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.chapter8.model.Order;
import org.chapter8.model.OrderStatus;
import org.chapter8.model.User;

public class Chapter8Section6 {

    public static void main(String[] args) {
        Map<Integer, String> numberMap = Stream.of(3, 5, -4, 2, 6)
//            .collect(Collectors.toMap(x -> x, x -> "Number is " + x));
            // 단순히 x - > x로 가는 함수는 Function.identity()로 대체할 수 있다.
            .collect(Collectors.toMap(Function.identity(), x -> "Number is " + x));
//        System.out.println(numberMap);

        User user1 = User.builder()
            .id(101)
            .name("Alice")
            .friendUserIds(Arrays.asList(201,202,203,204))
            .build();

        User user2 = User.builder()
            .id(102)
            .name("Bob")
            .friendUserIds(Arrays.asList(204,205,206))
            .build();

        User user3 = User.builder()
            .id(103)
            .name("Charlie")
            .friendUserIds(Arrays.asList(204,205,207))
            .build();
        List<User> users = Arrays.asList(user1, user2, user3);

        // Function.identity(): x -> x 로 간다.
        Map<Integer, User> userIdToUserMap = users.stream()
            .collect(Collectors.toMap(User::getId, Function.identity()));
//        System.out.println(userIdToUserMap);
//        System.out.println(userIdToUserMap.get(103));

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
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

        // Todo: Create a map from order id to order status
        Map<Long, OrderStatus> orderIdToOrderStatusMap = orders.stream()
            .collect(Collectors.toMap(Order::getId, Order::getStatus));
        System.out.println(orderIdToOrderStatusMap.get(1003L));
    }
}
