package org.chapter9.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private int id;
    private String name;
    private String emailAddress;
    private boolean isVerified;
    private LocalDateTime createdAt;
    private List<Integer> friendUserIds;

    @Builder
    public User(
        int id, String name, String emailAddress, boolean isVerified, LocalDateTime createdAt,
        List<Integer> friendUserIds
    ) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.isVerified = isVerified;
        this.createdAt = createdAt;
        this.friendUserIds = friendUserIds;
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
