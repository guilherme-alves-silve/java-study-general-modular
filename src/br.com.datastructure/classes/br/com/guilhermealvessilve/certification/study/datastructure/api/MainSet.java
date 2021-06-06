package br.com.guilhermealvessilve.certification.study.datastructure.api;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Alves
 */
public class MainSet {
    
    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>(20);
        Set<String> set3 = new HashSet<>(20, 0.85F);
        Set<String> set4 = new HashSet<>(List.of("a", "b", "c"));
        Set<String> set5 = Set.of("d");
        
        System.out.println(set1);
        System.out.println(set2);
        System.out.println(set3);
        System.out.println(set4);
        System.out.println(set5);
        
        System.out.println(set4.contains("a"));
        System.out.println(set4.containsAll(Set.of("a", "c")));
        System.out.println(set5.size());
    }
}
