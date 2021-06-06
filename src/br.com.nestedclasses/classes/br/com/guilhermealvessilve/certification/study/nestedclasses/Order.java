package br.com.guilhermealvessilve.certification.study.nestedclasses;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alves
 */
public class Order {
    
    private int countItemCreation;
    private int countOrderTaxManagerCreation;
    private final Map<Integer, Item> items = new HashMap<>();
    
    public Order addItem(String name, double price) {
        var item = new Item();
        item.name = name;
        item.price = BigDecimal.valueOf(Math.round(price * 100.0) / 100.0);
        items.put(countItemCreation, item);
        return this;
    }
    
    public void manageTaxes() {
        
        int localCount = 0;
        final var value1 = "print this";
        var value2 = "test";
        class OrderTaxManager {
            OrderTaxManager() {
                ++countOrderTaxManagerCreation;
                // ++localCount; //must be final or effectively final
                System.out.println("localCount: " + localCount);
                System.out.println("print 1: " + value1);
                System.out.println("print 2: " + value2);
            }
            
            BigDecimal calculateTaxes() {
                return items.values()
                        .stream()
                        .map(item -> item.price)
                        .reduce(BigDecimal.ZERO, (acc, value) -> acc.add(value).multiply(BigDecimal.valueOf(0.1)));
            }
        }
        
        var orderTaxManager = new OrderTaxManager();
        System.out.println("Taxes: " + orderTaxManager.calculateTaxes());
    }
    
    class Item {
        private String name;
        private BigDecimal price;
        
        Item() {
            ++countItemCreation;
        }
    }
}
