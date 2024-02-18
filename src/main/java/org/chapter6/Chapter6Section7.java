package org.chapter6;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.chapter6.model.Order;
import org.chapter6.model.OrderLine;
import org.chapter6.model.OrderLineType;

public class Chapter6Section7 {

    public static void main(String[] args) {

        String[][] cities = new String[][]{
            {"Seoul", "Busan"},
            {"San Francisco", "New York"},
            {"Madrid", "Barcelona"}
        };
        // 이 cities를 flated된 도시들의 스트림을 쭉 담고 있는 리스트를 만들고 싶다라고 했을 때 어떻게 하는지 알아보자
        Stream<String[]> cityStream = Arrays.stream(cities);
        Stream<Stream<String>> cityStreamX2 = cityStream.map(Arrays::stream);
        List<Stream<String>> cityStreamList = cityStreamX2.collect(Collectors.toList());

        Stream<String[]> cityStream2 = Arrays.stream(cities);
        Stream<String> flatternedCityStream = cityStream2.flatMap(Arrays::stream);
        List<String> flatternedCityList = flatternedCityStream.collect(Collectors.toList());
//        System.out.println(flatternedCityList);

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        Order order1 = Order.builder()
            .id(1001)
            .orderLines(
                Arrays.asList(
                    OrderLine.builder().id(10001).type(OrderLineType.PURCHASE)
                        .amount(BigDecimal.valueOf(5000)).build(),
                    OrderLine.builder().id(10002).type((OrderLineType.PURCHASE))
                        .amount(BigDecimal.valueOf(4000)).build()
                )).build();

        Order order2 = Order.builder()
            .id(1002)
            .orderLines(
                Arrays.asList(
                    OrderLine.builder().id(10003).type(OrderLineType.PURCHASE)
                        .amount(BigDecimal.valueOf(2000)).build(),
                    OrderLine.builder().id(10004).type((OrderLineType.DISCOUNT))
                        .amount(BigDecimal.valueOf(-1000)).build()
                )).build();

        Order order3 = Order.builder()
            .id(1003)
            .orderLines(
                Arrays.asList(
                    OrderLine.builder().id(10005).type(OrderLineType.PURCHASE)
                        .amount(BigDecimal.valueOf(2000)).build()
                )).build();


        List<Order> orders = Arrays.asList(order1, order2, order3);
        List<OrderLine> mergedOrderLines = orders.stream()  // Stream<Order>
            .map(Order::getOrderLines)                      // Stream<List<OrderLine>>
            .flatMap(List::stream)                          // Stream<Stream<OrderLIne>> -> Stream<OrderLine>
            .collect(Collectors.toList());
        System.out.println(mergedOrderLines);
    }
}
