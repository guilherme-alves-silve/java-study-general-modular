package br.com.guilhermealvessilve.certification.study.nestedclasses.packageprivate;

/**
 *
 * @author Alves
 */
public class MyOuter4Main {

    public static void main(String[] args) {
        var myOuter4 = new MyOuter4();
        myOuter4.create();
        
        //MyOuter4.MyInner4 created = myOuter4.create(); //Compilation error
    }
}
