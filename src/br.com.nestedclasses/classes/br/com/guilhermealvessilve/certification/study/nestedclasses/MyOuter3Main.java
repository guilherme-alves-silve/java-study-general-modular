package br.com.guilhermealvessilve.certification.study.nestedclasses;

/**
 *
 * @author Alves
 */
public class MyOuter3Main {
    
    public static void main(String[] args) {
        MyOuter3.MyInner3 inner = new MyOuter3().new MyInner3();
        System.out.println("inner: " + inner);
        
        MyOuter3 myOuter = new MyOuter3();
        MyOuter3.MyInner3 myInner = myOuter.new MyInner3();
        System.out.println("myInner: " + myInner);
    }
}
