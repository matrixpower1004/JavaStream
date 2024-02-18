package org.chapter10.service;

import org.chapter10.model.User;

public class MakeMoreFriendsEmailProvider implements EmailProvider {

    @Override
    public String getEmail(User user) {
        return "'Make More Friends' email for " + user.getName();
    }
}
