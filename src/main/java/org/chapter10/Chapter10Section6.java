package org.chapter10;

import java.math.BigDecimal;
import java.util.Arrays;
import org.chapter10.model.Order;
import org.chapter10.service.OrderProcessStep;
import org.chapter10.model.OrderLine;
import org.chapter10.model.OrderStatus;

public class Chapter10Section6 {

    public static void main(String[] args) {

        OrderProcessStep initializeStep = new OrderProcessStep(order ->
        {
            if (order.getStatus() == OrderStatus.CREATED) {
                System.out.println("Start processing order " + order.getId());
                order.setStatus(OrderStatus.IN_PROGRESS);
            }
        });

        OrderProcessStep setOrderAmountStep = new OrderProcessStep(order -> {
            if (order.getStatus() == OrderStatus.IN_PROGRESS) {
                System.out.println("Setting amount of order " + order.getId());
                order.setAmount(order.getOrderLines().stream().map(OrderLine::getAmount)
                     .reduce(BigDecimal.ZERO, BigDecimal::add));
            }
        });

        OrderProcessStep verifyOrderStep = new OrderProcessStep(order -> {
            if (order.getStatus().equals(OrderStatus.IN_PROGRESS)) {
                System.out.println("Verify order " + order.getId());
                if (order.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
                    order.setStatus(OrderStatus.ERROR);
                }
            }
        });

        OrderProcessStep processPaymentStep = new OrderProcessStep(order -> {
            if (order.getStatus().equals(OrderStatus.IN_PROGRESS)) {
                System.out.println("Processing payment of order " + order.getId());
                order.setStatus(OrderStatus.PROCESSED);
            }
        });

        OrderProcessStep handleErrorStep = new OrderProcessStep(order -> {
            if (order.getStatus().equals(OrderStatus.ERROR)) {
                System.out.println(
                    "Sending out 'Failed to process order' alert for order " + order.getId());
            }
        });

        OrderProcessStep completeProcessingOrderStep = new OrderProcessStep(order -> {
            if (order.getStatus().equals(OrderStatus.PROCESSED)) {
                System.out.println("Finished processing order " + order.getId());
            }
        });

        // 정의한 OrderProcess 들을 엮어서 workFlow를 만든다
        // 각 프로레스들은 자신이 프로세스 할 수 있는 것들만 프로세싱을 하기 때문에
        OrderProcessStep chainedOrderProcessStep = initializeStep
            .setNext(setOrderAmountStep)
            .setNext(verifyOrderStep)
            .setNext(processPaymentStep)
            .setNext(handleErrorStep) // 에러 스텝이 여기 들어가 있더라도 order가 에러 상태가 아니라면 skip을 한다.
            .setNext(completeProcessingOrderStep);

        Order order = Order.builder()
            .id(1001L)
            .status(OrderStatus.CREATED)
            .orderLines(Arrays.asList(
                OrderLine.builder().amount(BigDecimal.valueOf(1000)).build(),
                OrderLine.builder().amount(BigDecimal.valueOf(2000)).build()
            )).build();
//        chainedOrderProcessStep.process(order);

        Order failedOrder = Order.builder()
            .id(1001L)
            .status(OrderStatus.CREATED)
            .orderLines(Arrays.asList(
                OrderLine.builder().amount(BigDecimal.valueOf(1000)).build(),
                OrderLine.builder().amount(BigDecimal.valueOf(-2000)).build()
            )).build();
        chainedOrderProcessStep.process(failedOrder);
    }
}
