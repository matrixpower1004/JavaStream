package org.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.chapter8.model.User;
import org.chapter8.service.EmailService;

public class Chapter8Section8 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(13, 2, 101, 203, 304, 402, 305, 349, 2312, 203);
        // 이 numbers를 짝수인 그룹과 홀수인 그룹으로 쪼개보자.
        Map<Boolean, List<Integer>> numberPartitions = numbers.stream()
            .collect(Collectors.partitioningBy(number -> number % 2 == 0));
//        System.out.println("Even Number: " + numberPartitions.get(true));
//        System.out.println("Odd Number: " + numberPartitions.get(false));
        // 이렇게 partitioningBy는 어떤 조건을 만족하는 데이터들과 만족하지 않는 데이터들. 이렇게 두 그룹으로 분리를 할 때 자주 사용한다.

        User user1 = User.builder()
            .id(101)
            .name("Alice")
            .emailAddress("alice@fastcampus.co.kr")
            .friendUserIds(Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214))
            .build();

        User user2 = User.builder()
            .id(102)
            .name("Bob")
            .emailAddress("bob@fastcampus.co.kr")
            .friendUserIds(Arrays.asList(204,205,206))
            .build();

        User user3 = User.builder()
            .id(103)
            .name("Charlie")
            .emailAddress("charlie@fastcampus.co.kr")
            .friendUserIds(Arrays.asList(204, 205, 207, 218))
            .build();
        List<User> users = Arrays.asList(user1, user2, user3);

        // 친구 숫자가 5명을 넘으면 "친구랑 놀아 보세요"라는 이메일을 보내고
        // 친구 숫자가 5명 이하일 꼉우 "친구를 더 만들어 보세요"라는 이메일을 보낸다고 가정하자.
        Map<Boolean, List<User>> userPartitions = users.stream()
            .collect(Collectors.partitioningBy(user -> user.getFriendUserIds().size() > 5));
        System.out.println(userPartitions.get(true));

        EmailService emailService = new EmailService();
        for (User user : userPartitions.get(true)) {
            emailService.sendPlayWithFriendsEmail(user);
        }

        for (User user : userPartitions.get(false)) {
            emailService.sendMakerMoreFriendsEmail(user);
        }
    }
}
