package br.com.guilhermealvessilve.certification.study.utils;

/**
 *
 * @author Alves
 */
public class ArgsUtils {
    
    private ArgsUtils() {
        throw new IllegalArgumentException("No ArgsUtils!");
    }
    
    public static boolean requireMinSize(final String[] args, int size) {
        if (args.length != size) {
            System.err.println("Invalid required size! The number of arguments must be " + size);
            return false;
        }
        
        return true;
    }
}
