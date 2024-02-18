package org.chapter10.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED,
    IN_PROGRESS,
    ERROR,
    PROCESSED
}
