package ru.rassafel.streams.lecture;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rassafel
 */
public class Application {
    public static void main(String[] args) {
        Application application = new Application();
    }

    public void primitiveStreams() {
        List<Integer> integerList = new Random()
            .ints(10, 100)
            .peek(x -> System.out.println("Input value: " + x))
            .filter(x -> x % 2 != 0)
            .peek(x -> System.out.println("Filtered value: " + x))
            .limit(10)
            .map(x -> x * 2)
            .peek(x -> System.out.println("Output value: " + x))
            .boxed()
            .collect(Collectors.toList());
        System.out.println();
        System.out.println("integerList = " + integerList);

        List<Integer> mappedIntegerList = integerList.stream()
            .filter(x -> x % 5 == 0)
            .collect(Collectors.toList());

        System.out.println();
        System.out.println("integerList = " + integerList);
        System.out.println("mappedIntegerList = " + mappedIntegerList);
        System.out.println(integerList.equals(mappedIntegerList));
        System.out.println("integerList.getClass() = " + integerList.getClass());
    }
}
