package org.chapter4;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Chapter4Section2 {

    public static void main(String[] args) {
        Consumer<String> myStringConsumer = System.out::println;
//        myStringConsumer.accept("hello");
//        myStringConsumer.accept("world");

        List<Integer> integerInputs = Arrays.asList(4, 2, 3, 1);
        Consumer<Integer> myInterProcessor = x ->
            System.out.println("Processing integer " + x);
//        process(integerInputs, myInterProcessor);
        Consumer<Integer> myDifferentIntegerProcess = x ->
            System.out.println("Processing integer in different way " + x);
//        process(integerInputs, myDifferentIntegerProcess);

        Consumer<Double> myDoubleProcessor = x ->
            System.out.println("Processing double " + x);
        List<Double> doubleInputs = Arrays.asList(1.1, 2.2, 3.3);
        process(doubleInputs, myDoubleProcessor);
    }

    public static <T> void process(List<T> inputs, Consumer<T> processor) {
        for (T input : inputs) {
            processor.accept(input);
        }
    }
}
