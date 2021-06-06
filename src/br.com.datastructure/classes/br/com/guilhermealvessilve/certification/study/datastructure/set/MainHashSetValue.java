package br.com.guilhermealvessilve.certification.study.datastructure.set;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Alves
 */
public class MainHashSetValue {

    public static void main(String[] args) {
        final var set = new HashSetValue<Product>();
        set.add(new Product(1, "Product 1", 10.0));
        set.add(new Product(2, "Product 2", 20.0));
        set.add(new Product(3, "Product 3", 30.0));
        set.add(new Product(4, "Product 4", 40.0));
        
        System.out.println(set.get(Product.withId(1)));
        System.out.println(set.get(Product.withId(2)));
        System.out.println(set.get(Product.withId(3)));
        System.out.println(set.get(Product.withId(4)));
        
        set.remove(Product.withId(1)); //true
        
        System.out.println("******************");
        
        set.forEach(System.out::println);

        System.out.println("******************");
        
        set.retainAll(List.of(Product.withId(2), Product.withId(4)));
        set.forEach(System.out::println);
        
        System.out.println("******************");
        
        set.removeAll(List.of(Product.withId(4)));
        set.forEach(System.out::println);
        
        System.out.println("******************");
        
        set.add(new Product(6, "Product 6", 60.0));
        Arrays.stream(set.toArray())
                .forEach(System.out::println);
        
        System.out.println("******************");
        
        set.clear();
        System.out.println(set.isEmpty());
        set.forEach(System.out::println);
    }
    
    private static class Product {
        
        private int id;
        private String name;
        private double price;

        public Product() {
        }

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public Product setId(int id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Product setName(String name) {
            this.name = name;
            return this;
        }

        public double getPrice() {
            return price;
        }

        public Product setPrice(double price) {
            this.price = price;
            return this;
        }
        
        public static Product withId(int id) {
            return new Product(id, null, 0D);
        }
        
        @Override
        public int hashCode() {
            int hash = 5;
            hash = 53 * hash + this.id;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Product other = (Product) obj;
            return this.id == other.id;
        }

        @Override
        public String toString() {
            return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
        }
    }
}
