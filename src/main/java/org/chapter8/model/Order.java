package org.chapter8.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    private long id;
    private LocalDateTime createdAt;
    private long createdByUserId;
    private OrderStatus status;
    private BigDecimal amount;
    private List<OrderLine> orderLines;

    @Builder
    public Order(
        long id, LocalDateTime createdAt, long createdByUserId, OrderStatus status,
        BigDecimal amount,
        List<OrderLine> orderLines
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.createdByUserId = createdByUserId;
        this.status = status;
        this.amount = amount;
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", createdAt=" + createdAt +
            ", createdByUserId=" + createdByUserId +
            ", status=" + status +
            ", amount=" + amount +
            ", orderLines=" + orderLines +
            "}\n";
    }
}