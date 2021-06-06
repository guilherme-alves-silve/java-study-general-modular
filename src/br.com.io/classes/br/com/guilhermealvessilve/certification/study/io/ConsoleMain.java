package br.com.guilhermealvessilve.certification.study.io;

import java.io.Console;
import java.io.PrintWriter;

/**
 *
 * @author Alves
 */
public class ConsoleMain {
    
    public static void main(String[] args) {
        
        final Console console = System.console();
        if (null == console) {
            System.out.println("You don't have access to the console!");
            return;
        }
        
        console.writer()
                .append("Write some data about you! ")
                .append("In the line below! \n")
                .flush();
        
        final String infoAboutYou = console.readLine("Write about you %s");
        
        PrintWriter writer = console.writer();
        writer.format("The info about you is: %s", infoAboutYou);
        
        char[] password = console.readPassword("Writer your password");
        if (!new String(password).equals("12345")) {
            writer.println("Wrong password");
        } else {
            writer.println("Correct password!");
        }
    }
}
