package org.chapter9.priceprocessor;

import java.math.BigDecimal;
import java.util.function.Function;
import org.chapter9.model.Order;
import org.chapter9.model.OrderLine;

public class OrderLIneAggregationPriceProcessor implements Function<Order, Order> {


    @Override
    public Order apply(Order order) {
        return order.setAmount(order.getOrderLines().stream()
            .map(OrderLine::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
