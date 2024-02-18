package org.chapter10.service;

import org.chapter10.model.Price;

public class BasicPriceProcessor implements PriceProcessor {

    @Override
    public Price process(Price price) {
        return price;
    }
}
