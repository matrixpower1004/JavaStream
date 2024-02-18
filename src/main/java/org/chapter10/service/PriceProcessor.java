package org.chapter10.service;

import org.chapter10.model.Price;

@FunctionalInterface
public interface PriceProcessor {

    Price process(Price price);

    default PriceProcessor andThen(PriceProcessor next) {
        return price -> next.process(process(price));
    }
}
