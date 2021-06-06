package br.com.guilhermealvessilve.certification.study.datastructure.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Alves
 */
public class MainCollection {
    
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>(10);
        List<String> list3 = Arrays.asList("a", "b");
        List<String> list4 = List.of("c", "d");
        List<String> list5 = new ArrayList<>(List.of("ee", "ff"));
        
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
        System.out.println(list4);
        System.out.println(list5);
        
        System.out.println(list3.contains("a"));
        System.out.println(list4.containsAll(List.of("c", "d")));
        System.out.println(list1.isEmpty());
        System.out.println(list2.toString());
        
        Collections.sort(list3);
        System.out.println(list3);
        
        Collections.reverse(list3);
        System.out.println(list3);
        
        Collections.shuffle(list3);
        System.out.println(list3);
        
        Collections.fill(list3, "z");
        System.out.println(list3);
        
        final var arrayList = new CopyOnWriteArrayList<String>();
        arrayList.add("a");
    }
}
