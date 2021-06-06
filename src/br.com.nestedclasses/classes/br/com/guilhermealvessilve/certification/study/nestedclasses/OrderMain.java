package br.com.guilhermealvessilve.certification.study.nestedclasses;

/**
 *
 * @author Alves
 */
public class OrderMain {

    public static void main(String[] args) {
        Order order = new Order();
        order.addItem("item 1", 100.1212512)
            .addItem("item 1", 150.99)
            .addItem("item 1", 100.12416516161)
            .addItem("item 1", 150.306943795)
            .addItem("item 1", 900.2390212)
            .addItem("item 1", 50.437216922161);
        order.manageTaxes();
    }
}
