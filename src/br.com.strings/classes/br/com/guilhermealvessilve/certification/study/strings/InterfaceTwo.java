package br.com.guilhermealvessilve.certification.study.strings;

/**
 *
 * @author Alves
 */
public interface InterfaceTwo {
    
    int field = 99;
    
    default void method() {
        System.out.println(field);
    }
}
