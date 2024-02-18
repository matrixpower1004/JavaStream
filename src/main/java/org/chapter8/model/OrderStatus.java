package org.chapter8.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED,
    IN_PROGRESS,
    ERROR,
    PROCESSED
}
