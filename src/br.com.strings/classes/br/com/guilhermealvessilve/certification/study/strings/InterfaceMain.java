package br.com.guilhermealvessilve.certification.study.strings;

import java.util.function.BiFunction;
import java.util.function.DoublePredicate;

/**
 *
 * @author Alves
 */
public class InterfaceMain {
    
    public static void main(String[] args) {
        ClassOneTwo c = new ClassOneTwo();
        c.method();
        
        String munich = "Munich";
        TriFunction<String, Integer, Integer, String> convert = String::substring;
        System.out.println(convert.apply(munich, 1, 3));
        
        BiFunction<String, Integer, String> convert2 = String::substring;
        System.out.println(convert2.apply(munich, 2));
        
        DoublePredicate predicate = value -> value == 10.0;
        System.out.println(predicate.test(10.0));
    }
    
    @FunctionalInterface
    interface TriFunction<T1, T2, T3, R> {
    
        R apply(T1 t1, T2 t2, T3 t3);
    }
}
