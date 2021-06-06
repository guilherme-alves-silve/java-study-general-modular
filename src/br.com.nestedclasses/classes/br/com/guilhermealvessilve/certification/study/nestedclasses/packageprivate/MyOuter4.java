package br.com.guilhermealvessilve.certification.study.nestedclasses.packageprivate;

/**
 *
 * @author Alves
 */
public class MyOuter4 {

    public MyInner4 create() {
        final var inner = new MyInner4();
        inner.execute();
        return inner;
    }
    
    private class MyInner4 {
        public void execute() {
            System.out.println("Execute");
        }
    }
}
