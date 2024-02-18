package org.chapter10.model;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {

    private long id;
    private OrderLineType type;
    private long productId;
    private int quantity;
    private BigDecimal amount;

    @Builder
    public OrderLine(long id, OrderLineType type, long productId, int quantity, BigDecimal amount) {
        this.id = id;
        this.type = type;
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
            "id=" + id +
            ", type=" + type +
            ", productId=" + productId +
            ", quantity=" + quantity +
            ", amount=" + amount +
            "}\n";
    }
}
