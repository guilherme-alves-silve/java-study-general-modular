package br.com.guilhermealvessilve.certification.study.datastructure.hashtable.chaining;

import br.com.guilhermealvessilve.certification.study.datastructure.utils.TestUtils;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class MainHashTableChaining {

    public static void main(String[] args) {
        final int qty = 100;
        final var hashTable = new HashTable<Integer, String>();
        System.out.println("All must match: " + TestUtils.randomStream(0, qty)
                .allMatch(i -> hashTable.put(i, "Value-" + i) == null && hashTable.get(i) != null));
        System.out.println("Size must be 100: " + (qty == hashTable.size()));
        
        final var strHashTable = hashTable.toString();
        System.out.println("toString is correct: " + TestUtils.randomStream(0, qty)
                .allMatch(i -> strHashTable.contains("Value-" + i + "}")));
        
        System.out.println("None must match: " + TestUtils.randomStream(0, qty)
                .noneMatch(i -> hashTable.put(i, "Value-" + i) == null));
        System.out.println("Size must be 100: " + (qty == hashTable.size()));
        
        //Should not throw exception
        TestUtils.randomStream(0, qty * 10)
                .forEach(key -> hashTable.get(key));
        
        hashTable.printElementsPerBucket();
        
        System.out.println(hashTable);
        System.out.println("Size is " + qty + ": " + hashTable.size());
        System.out.println("Size is empty: " + hashTable.isEmpty());

        boolean removedAll = TestUtils.randomStream(0, qty)
        .allMatch(key -> {
            final var tempValue = hashTable.get(key);
            return tempValue != null && Objects.equals(hashTable.remove(key), tempValue);
        });
        
        System.out.println(removedAll);
        System.out.println(hashTable);
        System.out.println("Size is 0: " + hashTable.size());
        System.out.println("Size is empty: " + hashTable.isEmpty());
        
        hashTable.printElementsPerBucket();
    }
}
