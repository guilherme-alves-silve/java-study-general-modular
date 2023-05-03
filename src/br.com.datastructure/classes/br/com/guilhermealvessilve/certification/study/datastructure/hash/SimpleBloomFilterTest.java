package br.com.guilhermealvessilve.certification.study.datastructure.hash;

/**
 *
 * Reference:
 *  https://www.youtube.com/watch?v=0j5pa1MLeXA&ab_channel=DiegoPacheco
 *  http://diego-pacheco.blogspot.com/2021/07/java-bloom-filter.html
 * @author Alves
 */
public class SimpleBloomFilterTest {
    
    public static void main(String[] args) {
        var bloom = SimpleBloomFilter.builder()
                .size(1024)
                .hashFunctions(IntObjectsHash.build())
                .build();
        bloom.add(4);
        System.out.println("Is 4 present? " + bloom.mightContain(4)); // true
        
        for (int i = 10; i < 20; ++i) {
            bloom.add(i);
        }
        
        for (int i = 10; i < 20; ++i) {
            if (!bloom.mightContain(i)) {
                throw new IllegalStateException("mightContain is false: " + i);
            }
        }
        
        bloom = SimpleBloomFilter.builder()
                .size(1024)
                .hashFunctions(IntObjectsHash.build())
                .build();
        for (int i = 0; i < 99; ++i) {
            if (bloom.mightContain(i)) {
                throw new IllegalStateException("mightContain is true: " + i);
            }
        }
    }
}
