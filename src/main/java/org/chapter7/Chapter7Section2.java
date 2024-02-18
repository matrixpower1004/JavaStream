package org.chapter7;

import java.util.Optional;
import org.chapter7.model.User;

public class  Chapter7Section2 {

    public static void main(String[] args) {
        Optional<User> maybeUser = Optional.ofNullable(maybeGetUser(true));
//        maybeUser.ifPresent(System.out::println);

        Optional<Integer> maybeId = Optional.ofNullable(maybeGetUser(true))
            .map(user -> user.getId());
//        maybeId.ifPresent(System.out::println);

        // map은 여러 map을 체이닝 하는 것이 가능하다
        String userName = Optional.ofNullable(maybeGetUser(true))
            .map(User::getName)
            .map(name -> "The Name is " + name)
            .orElse("Name is empty");
//        System.out.println(userName);

        Optional<String> maybeEmail = Optional.ofNullable(maybeGetUser(true))
            .flatMap(User::getOptionalEmailAddress);
        maybeEmail.ifPresent(System.out::println);
    }

    public static User maybeGetUser(boolean returnUser) {
        if (returnUser) {
            return User.builder()
                .id(1001)
                .name("Alice")
                .emailAddress("alice@fastcampus.co.kr")
                .isVerified(false)
                .build();
        }
        return null;
    }
}
