package ru.rassafel.streams.lecture;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author rassafel
 */
public class WorkWithStreams {
    public static void main(String[] args) {
        WorkWithStreams workWithStreams = new WorkWithStreams();
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

    public void stringStream() {

        String string = Arrays.stream(new String[]{
                "Hello",
                " Test ",
                "Words",
                "Lorem",
                "Stream",
                "lorem"
            })
            .map(String::toLowerCase)
            .map(String::trim)
            .collect(Collectors.groupingBy(str -> str))
            .entrySet()
            .stream()
            .flatMap(ent -> ent.getValue().stream())
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(string);
    }
}
