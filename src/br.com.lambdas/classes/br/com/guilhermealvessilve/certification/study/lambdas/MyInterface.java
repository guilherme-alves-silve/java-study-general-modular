package br.com.guilhermealvessilve.certification.study.lambdas;

/**
 *
 * @author Alves
 */
@FunctionalInterface
public interface MyInterface {

    int THIS_IS_PUBLIC_STATIC_FINAL_A = 0;
    final int THIS_IS_PUBLIC_STATIC_FINAL_B = 1;
    static int THIS_IS_PUBLIC_STATIC_FINAL_C = 3;
    public static int THIS_IS_PUBLIC_STATIC_FINAL_D = 4;
    public static final int THIS_IS_PUBLIC_STATIC_FINAL_E = 5;
    static public final int THIS_IS_PUBLIC_STATIC_FINAL_F = 6;
    static final public int THIS_IS_PUBLIC_STATIC_FINAL_G = 7;
    
    void execute();
    
    public default void executeDefault() {
        executePrivate();
    }
    
    private void executePrivate() {
        System.out.println(THIS_IS_PUBLIC_STATIC_FINAL_A);
        System.out.println(THIS_IS_PUBLIC_STATIC_FINAL_B);
        System.out.println(THIS_IS_PUBLIC_STATIC_FINAL_C);
        System.out.println(THIS_IS_PUBLIC_STATIC_FINAL_D);
        System.out.println(THIS_IS_PUBLIC_STATIC_FINAL_E);
        System.out.println(THIS_IS_PUBLIC_STATIC_FINAL_F);
        System.out.println(THIS_IS_PUBLIC_STATIC_FINAL_G);
    }
}
