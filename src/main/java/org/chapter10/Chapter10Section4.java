package org.chapter10;

import java.util.Arrays;
import java.util.List;
import org.chapter10.model.User;
import org.chapter10.service.EmailProvider;
import org.chapter10.service.EmailSender;
import org.chapter10.service.MakeMoreFriendsEmailProvider;
import org.chapter10.service.VerifyYourEmailAddressEmailProvider;

public class Chapter10Section4 {

    public static void main(String[] args) {
        User user1 = User.builder(1, "Alice")
            .with(builder -> {
                builder.emailAddress = "alice@fastcampus.co.kr";
                builder.isVerified = false;
                builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212, 213, 214);
            }).build();
        User user2 = User.builder(2, "Bob")
            .with(builder -> {
                builder.emailAddress = "bob@fastcampus.co.kr";
                builder.isVerified = true;
                builder.friendUserIds = Arrays.asList(212, 213, 214);
            }).build();
        User user3 = User.builder(2, "Charlie")
            .with(builder -> {
                builder.emailAddress = "charlie@fastcampus.co.kr";
                builder.isVerified = true;
                builder.friendUserIds = Arrays.asList(201, 202, 203, 204, 211, 212);
            }).build();
        List<User> users = Arrays.asList(user1, user2, user3);

        EmailSender emailSender = new EmailSender();
        EmailProvider verifyYourEmailAddressEmailProvier = new VerifyYourEmailAddressEmailProvider();
        EmailProvider makeMoreFriendsEmailProvider = new MakeMoreFriendsEmailProvider();

        emailSender.setEmailProvider(verifyYourEmailAddressEmailProvier);
        users.stream()
            .filter(user21 -> !user21.isVerified())
            .forEach(emailSender::sendEmail);

        emailSender.setEmailProvider(makeMoreFriendsEmailProvider);
        users.stream()
            .filter(User::isVerified)
            .filter(user -> user.getFriendUserIds().size() <= 5)
            .forEach(emailSender::sendEmail);

        // 이렇게 전략을 실시간으로 만드는 것도 가능핟.
        emailSender.setEmailProvider(user -> "'Play With Friends' email for " + user.getName());
        users.stream()
            .filter(User::isVerified)
            .filter(user -> user.getFriendUserIds().size() > 5)
            .forEach(emailSender::sendEmail);
    }
}
