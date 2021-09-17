package br.com.guilhermealvessilve.certification.study.datastructure.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Alves
 */
public class TestUtils {
    
    private TestUtils() {
        throw new IllegalArgumentException("No TestUtils!");
    }
    
    public static List<Integer> randomList(int startInclusive, int endExclusive) {
        final var ints = IntStream.range(startInclusive, endExclusive)
                .collect(() -> new ArrayList<Integer>(), 
                        (list, intValue) -> list.add(intValue), 
                        (list1, list2) -> list1.addAll(list2));
        Collections.shuffle(ints);
        return ints;
    }
    
    public static Stream<Integer> randomStream(int startInclusive, int endExclusive) {
        return randomList(startInclusive, endExclusive).stream();
    }
}
