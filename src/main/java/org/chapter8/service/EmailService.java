package org.chapter8.service;

import org.chapter8.model.User;

public class EmailService {

    public void sendPlayWithFriendsEmail(User user) {
        user.getOptionalEmailAddress().ifPresent(
            email -> System.out.println("Sending 'Play With Friends' email to " + email));
    }

    public void sendMakerMoreFriendsEmail(User user) {
        user.getOptionalEmailAddress().ifPresent(
            email -> System.out.println("Sending 'Make More Friends' email to " + email));
    }

    public void sendVerifyYourEmail(User user) {
        user.getOptionalEmailAddress().ifPresent(
            email -> System.out.println("Sending 'Verify Your Email' email to "+ email)
        );
    }

}
