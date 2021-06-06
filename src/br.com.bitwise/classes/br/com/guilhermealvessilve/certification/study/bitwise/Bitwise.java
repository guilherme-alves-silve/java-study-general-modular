package br.com.guilhermealvessilve.certification.study.bitwise;

/**
 *
 * @author Alves
 */
public class Bitwise {
    
    public static void main(String[] args) {
        
        System.out.println("******************************************");
        for (int i = -9; i < 10; i++) {
            System.out.printf("%02d - %s%n", i, fillLeftWithZeros(Integer.SIZE, Integer.toBinaryString(i)));
        }
        
        System.out.println("\n**************** i << j ****************");
        for (int i = -9; i < 10; i++) {
            for (int j = -5; j <= 5; j++) {
                System.out.printf("%02d << %02d = %s = %d%n", i, j, fillLeftWithZeros(Integer.SIZE, Integer.toBinaryString(i << j)), i << j);
            }
        }
        
        System.out.println("\n**************** i >> j ****************");
        for (int i = -9; i < 10; i++) {
            for (int j = -5; j <= 5; j++) {
                System.out.printf("%02d >> %02d = %s = %d%n", i, j, fillLeftWithZeros(Integer.SIZE, Integer.toBinaryString(i >> j)), i >> j);
            }
        }
        
        System.out.println("\n**************** i >>> j ****************");
        for (int i = -9; i < 10; i++) {
            for (int j = -5; j <= 5; j++) {
                System.out.printf("%02d >>> %02d = %s = %d%n", i, j, fillLeftWithZeros(Integer.SIZE, Integer.toBinaryString(i >>> j)), i >>> j);
            }
        }
    }
    
    private static String fillLeftWithZeros(final int max, final String value) {
        final var builder = new StringBuilder(value);
        final int maxToInsert = max - value.length();
        for (int i = 0; i < maxToInsert; i++) {
            builder.insert(0, "0");
        }
        
        return builder.toString();
    }
}
