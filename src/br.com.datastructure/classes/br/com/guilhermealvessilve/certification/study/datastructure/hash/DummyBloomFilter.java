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
public class DummyBloomFilter implements BloomFilter<Integer> {

    private final int[] array;
    private final List<ToIntFunction<Integer>> hashFunctions;
    
    public DummyBloomFilter(int size) {
        if (Integer.bitCount(size) != 1) {
            throw new IllegalArgumentException("Consider size using " +
                    "1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096... Where Integer.bitCount(size) == 1");
        }
        
        this.array = new int[size];
        this.hashFunctions = H1H2IntHash.build();
    }
    
    @Override
    public void add(Integer value) {
        for (var hashFunction : hashFunctions) {
            int hashPosition = hashFunction.applyAsInt(value);
            array[hashPosition] = 1;
        }
    }

    @Override
    public boolean mightContain(Integer value) {
        int h1h2[] = new int[2];
        int i = 0;
        for (var hashFunction : hashFunctions) {
            int hashPosition = hashFunction.applyAsInt(value);
            h1h2[i] = array[hashPosition];
            ++i;
        }
        
        return (h1h2[0] == 1 && h1h2[1] == 1);
    }
}
