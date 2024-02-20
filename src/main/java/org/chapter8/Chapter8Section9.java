package org.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.chapter8.model.User;
import org.chapter8.service.EmailService;

public class Chapter8Section9 {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, 5, 2, 1);
//        numbers.stream().forEach(number -> System.out.println("The number is " + number));
        numbers.forEach(number -> System.out.println("The number is " + number));
        // 리스트도 iterable이기 때문에 중간에 처리할 스트림이 없다면 바로 forEach를 호출 해도 된다.

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

        // Verified 되지 않은 유저들에게만 이메일을 보낸다.
        EmailService emailService = new EmailService();

        users.stream()
            .filter(user -> !user.isVerified())
            .forEach(emailService::sendVerifyYourEmail);
        // 지난 시간에 우리가 했던 예제들도 메서드 레퍼런스와 forEach까지 이용하면 더 간단하게 만들 수 있다.
        // 아예 각 for문을 한줄로 만들 수 있다. -> Todo 항목

        // 기존의 for문이 index를 사용했다면?
//        for (int i = 0; i < users.size(); i++) {
//            System.out.println("Do an operation on user " + user1.getName() + " at index " + i);
//        }

        // intStream 은 어떤 범위의 숫자를 스트림을 통해서 하나씩 제공해준다. 이걸 이용하면 index를 이용한 for문도 쉽게 대체가 가능하다.
        IntStream.range(0, users.size()).forEach(i -> {
            System.out.println("Do an operation on user " + user1.getName() + " at index " + i);
        });
    }
}
