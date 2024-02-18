package org.chapter10.service;

import org.chapter10.model.User;

public class UserService extends AbstractUserService {

    @Override
    protected boolean validateUser(User user) {
        System.out.println("Validating user " + user.getName());
        return user.getName() != null && user.getOptionalEmailAddress().isPresent();
    }

    @Override
    protected void writeToDB(User user) {
        System.out.println("Writing user " + user.getName() + "to DB");
    }
}
