package br.com.guilhermealvessilve.certification.study.strings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alves
 */
public class VarMain {
    
    //private var breed = "Shepard"; //can't
    
    public static void main(String[] args) {
        
        // var array = {1, 2, 3}; //can't
        //var price = 0.1, tax = 2.0; //can't
        var number = 1;
        var list = List.of("A", "B", "C");
        var var = "";
        var a = 1___3;
        
        var list2 = List.of(1, 2, 3, 4 , 5);
        list2.forEach(x -> x = x + 1);
        list2.forEach(System.out::println);
        
        var map = Map.of(1, "A", 2, "B", 3, "C");
        map.forEach((var i, final var p) -> {
            i += 10;
        });
        
        List<Integer> ls = Arrays.asList(10, 47, 33, 23);
        int max = ls.stream().max(Comparator.comparing(k -> k)).get();
        System.out.println(max);
    }
}
