package org.cahpter3;

import java.util.function.Function;

public class Chapter3Section1 {

    public static void main(String[] args) {
        Function<Integer, Integer> myAdder = new Adder();
        Integer result = myAdder.apply(5);
        System.out.println(result  );
    }
}
