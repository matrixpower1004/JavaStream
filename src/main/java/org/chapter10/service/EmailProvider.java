package org.chapter10.service;

import org.chapter10.model.User;

public interface EmailProvider {

    String getEmail(User user);

}
