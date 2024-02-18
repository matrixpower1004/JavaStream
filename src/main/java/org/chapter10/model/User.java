package org.chapter10.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import lombok.Getter;

@Getter
public class User {

    private int id;
    private String name;
    private String emailAddress;
    private boolean isVerified;
    private LocalDateTime createdAt;
    private List<Integer> friendUserIds;

    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.emailAddress = builder.emailAddress;
        this.isVerified = builder.isVerified;
        this.createdAt = builder.createdAt;
        this.friendUserIds = builder.friendUserIds;
    }

    public Optional<String> getOptionalEmailAddress() {
        return Optional.ofNullable(this.emailAddress);
    }

    public User changeCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public User changeName(String userName) {
        this.name = userName;
        return this;
    }

    public User changeVerified(boolean verified) {
        isVerified = verified;
        return this;
    }

    // Builder를 리턴해주는 메서드를 UserClass안에 만들어준다. Builder의 생성자는 private라 외부에서 접근이 불가능하다.
    public static Builder builder(int id, String name) {
        return new Builder(id, name);
    }

    public static class Builder {
        private int id;
        private String name;
        public String emailAddress;
        public boolean isVerified;
        public LocalDateTime createdAt;
        public List<Integer> friendUserIds = new ArrayList<>();

        private Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder with(Consumer<Builder> consumer) {
            consumer.accept(this);
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", emailAddress='" + emailAddress + '\'' +
            ", isVerified=" + isVerified +
            ", friendUserIds=" + friendUserIds +
            "}\n";
    }
}
