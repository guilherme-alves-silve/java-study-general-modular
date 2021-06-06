package br.com.guilhermealvessilve.certification.study.lambdas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alves
 */
public class LambdaMain {
    
    public static void main(String[] args) {
        final List<String> list = new ArrayList<>();
        list.add("remove me 1");
        list.add("remove me 2");
        list.add("won't remove me");
        System.out.println("list: " + list);
        list.removeIf((final String value) -> "remove me 1".equals(value));
        list.removeIf((final var value) -> "remove me 2".equals(value));
        list.removeIf(value -> {
           value = "";
           return value.equals("won't remove me");
        });
        list.removeIf((value) -> {
           value = "";
           return value.equals("won't remove me");
        });
        list.removeIf((var value) -> {
           value = "";
           return value.equals("won't remove me");
        });
        System.out.println("list: " + list);
    }
}
