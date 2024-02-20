package org.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.chapter8.model.User;
import org.chapter8.service.EmailService;

public class Chapter8Section10 {

    public static void main(String[] args) {

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
        User user4 = User.builder()
            .id(104)
            .name("David")
            .isVerified(true)
            .emailAddress("david@fastcampus.co.kr")
            .build();
        User user5 = User.builder()
            .id(105)
            .name("Eve")
            .isVerified(false)
            .emailAddress("eve@fastcampus.co.kr")
            .build();
        User user6 = User.builder()
            .id(106)
            .name("Frank")
            .isVerified(false)
            .emailAddress("frank@fastcampus.co.kr")
            .build();
        List<User> users = Arrays.asList(user1, user2, user3, user4, user5, user6);

        // 검증되지 않은 유저들을 골라내는 것, Email을 보내는 것 모두 순서와 상관이 없다.
        // 순차 처리 했을 때와 병렬 처리 했을 때의 속도 차이를 비교해 보자.
        // 약간 더 유의미한 차이를 보기 위해서 유저를 추가한다.
//        long startTime = System.currentTimeMillis();
//        EmailService emailService = new EmailService();
//        users.stream()
//            .filter(user -> !user.isVerified())
//            .forEach(emailService::sendVerifyYourEmail);
//        long endTime = System.currentTimeMillis();
//        System.out.println("Sequential: " + (endTime - startTime + "ms") );


        // 똑같은 코드를 이번에는 병렬처리해 보자.
//        startTime = System.currentTimeMillis();
//        users.stream().parallel()
//            .filter(user -> !user.isVerified())
//            .forEach(emailService::sendVerifyYourEmail);
//        endTime = System.currentTimeMillis();
//        System.out.println("Parallel: " + (endTime - startTime + "ms") );

        List<User> processedUsers = users.parallelStream()
            .map(user -> {
                System.out.println("Capitalize user name for user" + user.getId());
                user.changeName(user.getName().toUpperCase());
                return user;
            })
            .map(user -> {
                System.out.println("Set 'isVerified' to  true for user " + user.getId());
                user.changeVerified(true);
                return user;
            })
            .collect(Collectors.toList());
        System.out.println(processedUsers);
    }
}
