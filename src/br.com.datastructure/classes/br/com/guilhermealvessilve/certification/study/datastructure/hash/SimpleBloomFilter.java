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
public class SimpleBloomFilter<T> implements BloomFilter<T> {

    private final int size;
    private final long[] array;
    private final List<ToIntFunction<T>> hashFunctions;
    
    public SimpleBloomFilter(long[] array, int logicalSize, List<ToIntFunction<T>> hashFunctions) {
        this.array = array;
        this.size = logicalSize;
        this.hashFunctions = hashFunctions;
    }
    
    public static <T> Builder<T> builder() {
        return new Builder<>();
    }
    
    public static class Builder<T> {
        private int size;
        private List<ToIntFunction<T>> hashFunctions;
    
        public Builder<T> size(int size) {
            if (Integer.bitCount(size) != 1) {
                throw new IllegalArgumentException("Consider size using " +
                        "1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096... Where Integer.bitCount(size) == 1");
            }

            this.size = size;
            return this;
        }
        
        public Builder<T> hashFunctions(List<ToIntFunction<T>> hashFunctions) {
            this.hashFunctions = hashFunctions;
            return this;
        }
        
        public SimpleBloomFilter<T> build() {
            return new SimpleBloomFilter<>(new long[size >>> 6], size, hashFunctions);
        }
    }

    private int mapHash(int hash) {
        return hash & (size - 1);
    }
    
    @Override
    public void add(T value) {
        for (var hashFunction : hashFunctions) {
            int hash = mapHash(hashFunction.applyAsInt(value));
            array[hash >>> 6] |= 1L << hash;
        }
    }

    @Override
    public boolean mightContain(T value) {
        for (var hashFunction : hashFunctions) {
            int hash = mapHash(hashFunction.applyAsInt(value));
            if ((array[hash >>> 6] & (1L << hash)) == 0) {
                return false;
            }
        }
        
        return true;
    }
    
    
}
