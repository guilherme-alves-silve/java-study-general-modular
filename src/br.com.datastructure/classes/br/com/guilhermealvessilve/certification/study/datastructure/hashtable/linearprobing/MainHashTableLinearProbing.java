package br.com.guilhermealvessilve.certification.study.datastructure.hashtable.linearprobing;

import br.com.guilhermealvessilve.certification.study.datastructure.utils.TestUtils;
import java.util.Objects;

/**
 *
 * @author Alves
 */
public class MainHashTableLinearProbing {

    public static void main(String[] args) {
        final int qty = 100;
        final var hashTable = new HashTableLinearProbing<Integer, String>();
        System.out.println("All must match: " + TestUtils.randomStream(0, qty)
                .allMatch(i -> hashTable.put(i, "Value-" + i) == null && hashTable.get(i) != null));
        System.out.format("Size must be %d: %b %n", qty, (qty == hashTable.size()));
        
        final var strHashTable = hashTable.toString();
        System.out.println("toString is correct: " + TestUtils.randomStream(0, qty)
                .allMatch(i -> strHashTable.contains("Value-" + i + "}")));
        
        System.out.println("None must match: " + TestUtils.randomStream(0, qty)
                .noneMatch(i -> hashTable.put(i, "Value-" + i) == null));
        System.out.format("Size must be %d: %b %n", qty, (qty == hashTable.size()));
        
        //Should not throw exception
        TestUtils.randomStream(0, qty * 10)
                .forEach(key -> hashTable.get(key));
        
        System.out.println(hashTable);
        System.out.println("Size is " + qty + ": " + hashTable.size());
        System.out.println("hashTable is empty: " + hashTable.isEmpty());

        hashTable.printElementsPerBucket();
        
        boolean removedAll = TestUtils.randomStream(0, qty)
        .allMatch(key -> {
            final var tempValue = hashTable.get(key);
            return tempValue != null && Objects.equals(hashTable.remove(key), tempValue);
        });
        
        System.out.println(removedAll);
        System.out.println(hashTable);
        System.out.println("Size is 0: " + hashTable.size());
        System.out.println("hashTable is empty: " + hashTable.isEmpty());
    }
}
