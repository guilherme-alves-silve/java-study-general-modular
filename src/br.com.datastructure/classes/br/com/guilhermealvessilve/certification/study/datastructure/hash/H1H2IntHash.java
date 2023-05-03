package br.com.guilhermealvessilve.certification.study.datastructure.hash;

import java.util.List;
import java.util.function.ToIntFunction;

/**
 *
 * Reference:
 *  https://www.youtube.com/watch?v=0j5pa1MLeXA&ab_channel=DiegoPacheco
 *  http://diego-pacheco.blogspot.com/2021/07/java-bloom-filter.html
 * @author Alves
 */
class H1H2IntHash {

    private static class H1Hash implements ToIntFunction<Integer> {

        @Override
        public int applyAsInt(Integer value) {
            int hash = value % 5;
            return hash;
        }
    }
    
    private static class H2Hash implements ToIntFunction<Integer> {

        @Override
        public int applyAsInt(Integer value) {
            int hash = ((2 * value) + 3) % 5;
            return hash;
        }
    }
    
    static List<ToIntFunction<Integer>> build() {
        return List.of(new H1Hash(), new H2Hash());
    }
}
