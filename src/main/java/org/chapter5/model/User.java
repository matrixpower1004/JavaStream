package org.chapter5.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private int id;
    private String name;

    @Builder
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
}
