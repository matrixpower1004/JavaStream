package org.chapter6;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.chapter6.model.Order;
import org.chapter6.model.OrderStatus;
import org.chapter6.model.User;

public class Chapter6Section3 {

    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(3, 6, -4);
//        Stream<Integer> numberStream = numberList.stream();
//        Stream<Integer> numberStreamX2 = numberStream.map(x -> x * 2);
//        List<Integer> numberListX2 = numberStreamX2.collect(Collectors.toList());
//        System.out.println(numberListX2);

        // 위의 코드를 아래와 같이 한번에 변환할 수 있다.
        List<Integer> numberListX2 = numberList.stream()
            .map(x -> x * 2)
            .collect(Collectors.toList());
//        System.out.println(numberListX2);

        Stream<Integer> numberListStream = numberList.stream();
        Stream<String> stringStream = numberListStream.map(x -> "Number is " + x);
        List<String> strList = stringStream.collect(Collectors.toList());
//        System.out.println(strList);

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

        // users에서 email만 뽑아내서 email만 들어있는 list를 만들어보자.
//        Stream<User> userStream = users.stream();
//        Stream<String> userEmailStream = userStream.map(User::getEmailAddress);
//        List<String> emailAddresses  = userEmailStream.collect(Collectors.toList());
        // 위의 예제와 같이 여러단계를 거치지 않고 한번에 변환이 가능핟.
        List<String> emailAddresses = users.stream()
            .map(User::getEmailAddress)
            .collect(Collectors.toList());
//        System.out.println(emailAddresses);

        // Order를 이용한 예제
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
            .createdByUserId(101)
            .build();

        List<Order> orders = Arrays.asList(order1, order2, order3, order4, order5);

        // TODO: Create list of createdByUserId
        List<Long> createdByUserIds = orders.stream()
            .map(Order::getCreatedByUserId)
            .collect(Collectors.toList());
        System.out.println(createdByUserIds);
    }
}
