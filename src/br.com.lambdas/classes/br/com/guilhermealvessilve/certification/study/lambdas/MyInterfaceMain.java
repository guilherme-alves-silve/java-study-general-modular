package br.com.guilhermealvessilve.certification.study.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Alves
 */
public class MyInterfaceMain {
    
    public static void main(String[] args) {
        MyInterface func = () -> System.out.println("EXECUTED");
        func.execute();
        
        var func2 = new MyInterface() {
            @Override
            public void execute() {
                this.executeDefault();
            }
        };
        
        func2.execute();

        var list1 = new ArrayList<String>(Arrays.asList("B", "A", "C", null, null));
        var list2 = new ArrayList<String>(Arrays.asList(null, null, "B", "C", "A"));
        
        Comparator<String> sort1 = (v1, v2) -> String.CASE_INSENSITIVE_ORDER.compare(v1, v2);
        Comparator<String> sort2 = (v1, v2) -> String.CASE_INSENSITIVE_ORDER.compare(v2, v1);
        Collections.sort(list1, Comparator.nullsFirst(sort1));
        Collections.sort(list2, Comparator.nullsLast(sort2));
        
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        
        var list3 = new ArrayList<String>(List.of("A", "B", "C", "D", "E", "F","G", "H", "I", "J", "K"));
        list3.removeIf(Predicate.isEqual("C"));
        list3.removeIf(Predicate.isEqual("F"));
        list3.removeIf(Predicate.isEqual("G").or(other -> other.equals("H")));
        list3.removeIf(Predicate.isEqual("I").or(Predicate.isEqual("K")));
        System.out.println("list3: " + list3);
    }
}
