package org.chapter10.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Price {

    private String price;

    @Builder
    public Price(String price) {
        this.price = price;
    }
}
