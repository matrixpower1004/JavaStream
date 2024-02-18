package org.chapter9;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.chapter9.model.Order;
import org.chapter9.model.OrderLine;
import org.chapter9.priceprocessor.OrderLIneAggregationPriceProcessor;
import org.chapter9.priceprocessor.TaxPriceProcessor;

public class Chapter9Section3 {

    public static void main(String[] args) {

        Function<Integer, Integer> multiplyByTwo = x -> 2 * x;
        Function<Integer, Integer> addTen = x -> x + 10;

        Function<Integer, Integer> composedFunction = multiplyByTwo.andThen(addTen);
//        System.out.println(composedFunction.apply(3));;

        Order unprocessdOrder = Order.builder()
            .id(1001)
            .orderLines(Arrays.asList(
                OrderLine.builder().amount(BigDecimal.valueOf(1000)).build(),
                OrderLine.builder().amount(BigDecimal.valueOf(2000)).build()
            ))
            .build();
        List<Function<Order, Order>> priceProcessors = getPriceProcessors(unprocessdOrder);

        // 그 다음에 이 priceProcessor들을 하나로 합쳐서 합성된 priceProcessor를 넘기는데
        // 이 PriceProcessor는 최종적으로 Order를 받아서 Order는 넘기는 함수가 된다.
        Function<Order, Order> mergePriceProcessor = priceProcessors.stream()
            .reduce(Function.identity(), Function::andThen);
            // 함수들들 전부 andThen으로 엮어주면 되는데 이럴 때는 reduce를 쓸 수 있다.
        Order processedOrder = mergePriceProcessor.apply(unprocessdOrder);
        System.out.println(processedOrder);

    }

    public static List<Function<Order, Order>> getPriceProcessors(Order order) {
        return Arrays.asList(
            new OrderLIneAggregationPriceProcessor(),
            new TaxPriceProcessor(new BigDecimal("9.375"))
        );
        // BigDecimal을 쓰기 때문에 소수점 자리가 여러 자리로 내려가더라도 float나 double을 쓸 때처럼 주의를 할 필요가 없다.
    }
}
