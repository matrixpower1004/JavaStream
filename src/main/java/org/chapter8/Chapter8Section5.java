package org.chapter8;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter8Section5 {

    public static void main(String[] args) {

        List<Integer> numbers = Stream.of(3, 5, -3, 3, 4, 5)
            .collect(Collectors.toList());
//        System.out.println(numbers);

        Set<Integer> numberSet = Stream.of(3, 5, -3, 3, 4, 5)
            .collect(Collectors.toSet());
        System.out.println(numberSet);

        // map을 적용하고 collect를 한 것과 결과값이 똑같다.
        List<Integer> numberList2 = Stream.of(3, 5, -3, 3, 4, 5)
            .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toList()));
        System.out.println(numberList2);

        List<Integer> numberList3 = Stream.of(3, 5, -3, 3, 4, 5)
            .map(x -> Math.abs(x))
            .collect(Collectors.toList());
        System.out.println(numberList3);

        Set<Integer> numberSet2 = Stream.of(3, 5, -3, 3, 4, 5)
            .collect(Collectors.mapping(x -> Math.abs(x), Collectors.toSet()));
        System.out.println(numberSet2);

        int sum = Stream.of(3, 5, -3, 3, 4, 5)
            .collect(Collectors.reducing(0, (x, y) -> x + y));
        System.out.println(sum);
    }
}
