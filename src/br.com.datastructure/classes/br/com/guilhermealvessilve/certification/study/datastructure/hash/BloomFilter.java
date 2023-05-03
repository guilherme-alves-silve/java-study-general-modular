package br.com.guilhermealvessilve.certification.study.datastructure.hash;

/**
 *
 * Reference:
 *  https://www.youtube.com/watch?v=0j5pa1MLeXA&ab_channel=DiegoPacheco
 *  http://diego-pacheco.blogspot.com/2021/07/java-bloom-filter.html
 * @author Alves
 */
public interface BloomFilter<T> {
    
    void add(T value);
    
    boolean mightContain(T value);
}
