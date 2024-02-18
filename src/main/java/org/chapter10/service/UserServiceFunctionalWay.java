package org.chapter10.service;

import java.util.function.Consumer;
import java.util.function.Predicate;
import org.chapter10.model.User;

public class UserServiceFunctionalWay {

    private final Predicate<User> validatorUser;

    private final Consumer<User> writeToDB;

    public UserServiceFunctionalWay(
        Predicate<User> validatorUser, Consumer<User> writeToDB
    ) {
        this.validatorUser = validatorUser;
        this.writeToDB = writeToDB;
    }

    public void createUser(User user) {
        if (validatorUser.test(user)) {
            writeToDB.accept(user);
        } else {
            System.out.println("Cannot create user");
        }
    }
}
