package org.example.deutsche;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArraySortByFrequency {
    public static void main(String[] args) {
        // Given an input Integer array with repetitive values, return the output sorted in terms of their frequency in descending order.
        // Eg: input = [1, 6, 5 ,4 ,7, 4, 5, 1, 5, 7, 2, 2, 2, 2]
        //     output = [5, 5, 5, 7,7, 4, 4, 1, 1, 6, 2]
        Integer[] input = new Integer[] {1, 6, 5, 4, 7, 4, 5, 1, 5, 7, 2, 2, 2, 2};
        Map<Integer, Long> map = Arrays.stream(input).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);

        List<Integer> result = new LinkedList<>();
        map.entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByValue().reversed()
                .thenComparing(Map.Entry.<Integer, Long>comparingByKey().reversed()))
                .forEach(m -> {
                    for(int i=0; i<m.getValue(); i++) {
                    result.add(m.getKey());
                }
        });
        System.out.println(result);

    }
}
