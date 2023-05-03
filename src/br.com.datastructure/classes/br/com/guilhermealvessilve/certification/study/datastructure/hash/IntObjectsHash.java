package br.com.guilhermealvessilve.certification.study.datastructure.hash;

import java.util.List;
import java.util.Objects;
import java.util.function.ToIntFunction;

/**
 *
 * Reference:
 *  https://www.youtube.com/watch?v=0j5pa1MLeXA&ab_channel=DiegoPacheco
 *  http://diego-pacheco.blogspot.com/2021/07/java-bloom-filter.html
 * @author Alves
 */
class IntObjectsHash<T> implements ToIntFunction<T> {

    @Override
    public int applyAsInt(T value) {
        int hash = Objects.hash(value);
        return hash;
    }
    
    public static <T> List<ToIntFunction<T>> build() {
        return List.of(new IntObjectsHash<>());
    }
}
