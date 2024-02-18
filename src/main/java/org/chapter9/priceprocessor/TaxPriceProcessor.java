package org.chapter9.priceprocessor;

import java.math.BigDecimal;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.chapter9.model.Order;

@RequiredArgsConstructor
public class TaxPriceProcessor implements Function<Order, Order> {

    private final BigDecimal taxRate;

    @Override
    public Order apply(Order order) {
        return order.setAmount(
            order.getAmount().multiply(taxRate.divide(new BigDecimal(100)).add(BigDecimal.ONE)));
        // %니까 먼저 100으로 나눠준다. 그래서 0.0098의 현태가 되었을 때 곱해주려면 여기에 1을 더해줘야 한다.
        // 그래서 1.0098 이런 형태가 된 다음에 곱해줘야 한다.
    }
}
