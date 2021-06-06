package br.com.guilhermealvessilve.certification.study.nestedclasses.packageprivate;

/**
 *
 * @author Alves
 */
public class MyOuter2 {
    
    public static MyInner2 create() {
        var inner =  new MyInner2();
        inner.execute();
        return inner;
    }
    
    private static class MyInner2 {
        public void execute() {
            System.out.println("EXECUTE");
        }
    }
}
