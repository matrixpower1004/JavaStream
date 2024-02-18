package org.chapter7;

import java.util.Optional;
import org.chapter7.model.User;

public class Chapter7Section1 {

    public static void main(String[] args) {

        User user1 = User.builder()
            .id(1001)
            .name("Alice")
            .isVerified(false)
            .build();

        User user2 = User.builder()
            .id(1001)
            .name("Alice")
            .isVerified(false)
            .emailAddress("alice@fastcampus.co.kr")
            .build();
//        System.out.println("Same : " + userEquals(user2, user1));
        // 만약 user1부터 넣는다면 결과가 어떻게 될까?
//        System.out.println("Same : " + userEquals(user1, user2));
        // NPE 발생. user1은 emailAddress가 지정이 되지 않았기 때문이다.
        // 같은 파라미터들이지만 단순히 순서를 바꿔 넣은 것 만으로도 같은지 비교해 주는 함수가 다른 행동을 한다.
        // Null에 대한 체크를 제대로 하지 않았기 때문이다.

        String someEmail = "some@email.com";
        String nullEmail = null;

        Optional<String> maybeEmail1 = Optional.of(someEmail);
        Optional<String> maybeEmail2 = Optional.empty();
        Optional<String> maybeEmail3 = Optional.ofNullable(someEmail);
        Optional<String> maybeEmail4 = Optional.ofNullable(nullEmail);

        String email = maybeEmail1.get();
//        System.out.println(email);
//        String email2 = maybeEmail2.get();
        if (maybeEmail2.isPresent()) {
            System.out.println(maybeEmail2.get());
        }

        String defaultEmail = "default";
        String email3 = maybeEmail2.orElse(defaultEmail);
//        System.out.println(email3);
        String email4 = maybeEmail2.orElseGet(() -> defaultEmail);
//        System.out.println(email4);
        String email5 = maybeEmail2.orElseThrow(() -> new RuntimeException("email not present"));
    }

    private static boolean userEquals(User u1, User u2) {
        return u1.getId() == u2.getId()
            && u1.getName().equals(u2.getName())
            && u1.getEmailAddress().equals(u2.getEmailAddress())
            && u1.isVerified() == u2.isVerified();
    }
}
