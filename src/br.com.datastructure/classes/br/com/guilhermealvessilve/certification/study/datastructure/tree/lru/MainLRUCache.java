package br.com.guilhermealvessilve.certification.study.datastructure.tree.lru;

/**
 *
 * @author Alves
 */
public class MainLRUCache {
    
    public static void main(String[] args) {
        var cache = new LRUCache<String, Integer>(5);
        cache.put("A", 0);
        cache.put("B", 1);
        cache.put("C", 2);
        cache.put("D", 3);
        cache.put("E", 4);
        cache.printNodes();
        
        System.out.println("C(2) = " + cache.get("C"));
        cache.printNodes();
        
        System.out.println("D(3) = " + cache.get("D"));
        cache.printNodes();
        
        cache.put("F", 5);
        cache.printNodes();
        
        cache.put("G", 6);
        cache.printNodes();
    }
}
