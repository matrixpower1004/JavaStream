package org.chapter9;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Chapter9Section2 {

    public static void main(String[] args) {
        /*
            returnTure()와 returnFalse()가 모두 계산이 된 후에야 true가 출력된다.
            이유는 or() 메서드 호출전에 boolean x의 값과 boolean y 의 값을 모두 알고 나서야 메서드를 호출하기 때문이다.
         */

//        if (returnTrue() || returnFalse()) { // 뒤에 어떤 조건이 오든 무조건 true이기 때문에 뒤의 조건은 실행되지 않는다.
//            System.out.println("true");
//        }

//        if (or(returnTrue(), returnFalse())) {
//            System.out.println("true");
//        }

        // 만약 returnTure()와 returnFalse()가 자원이 많이 드는 비싼 게산이라면?
        // 최적화를 할 수 있다면 최적화를 해서 계산을 안 할 수 있다면 안하도록 하려면 어떤 방법을 써야 할까?
        // 이때 LazyEvaluation을 사용할 수 있다.
//        if (lazyOr(() -> returnTrue(), () -> returnFalse())) {
//            System.out.println("true");
//        }
        // 거의 똑같은 형태지만 이 경에는 returnFalse()가 실행이 되지 않는다.
        // 이런 종류의 LazyEvaluation을 스트림에서도 볼 수 있다.

        Stream<Integer> integerStream = Stream.of(3, -2, 5, 8, -3, 10)
            .filter(x -> x > 0)
            .peek(x -> System.out.println("peeking " + x))
            .filter(x -> x % 2 == 0);
        System.out.println("Before collect");

        List<Integer> integers = integerStream.toList();
        System.out.println("After collect: " + integers);
    }

    public static boolean or(boolean x, boolean y) {
        return x || y;
    }

    public static boolean lazyOr(Supplier<Boolean> x, Supplier<Boolean> y) {
        return x.get() || y.get();
    }

    public static boolean returnTrue() {
        System.out.println("Returning true");
        return true;
    }

    public static boolean returnFalse() {
        System.out.println("Returning false");
        return false;
    }
}
