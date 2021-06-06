package br.com.guilhermealvessilve.certification.study.nestedclasses;

/**
 *
 * @author Alves
 */
public class MyOuterMain {
    
    public static void main(String[] args) {
        
        MyOuter.MyInner inner = new MyOuter.MyInner();
        System.out.println(inner);
    }
}
