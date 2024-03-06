package org.chapter4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.chapter4.model.User;

public class Chapter4Section5 {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add((new User(3, "Alice")));
        users.add((new User(1, "Charlie")));
        users.add((new User(5, "Bob")));
//        System.out.println(users);

        Comparator<User> idComparator = (u1, u2) -> u1.getId() - u2.getId();
        users.sort(idComparator);
//        System.out.println(users);

        users.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
//        System.out.println(users);

        users.sort(Comparator.comparing(User::getId));
        System.out.println(users);

    }
}
