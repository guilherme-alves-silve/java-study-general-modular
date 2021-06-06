package br.com.guilhermealvessilve.certification.study.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/**
 *
 * @author Alves
 */
public class DeletePathsMain {

    public static void main(String[] args) throws IOException {
        Path backup = Path.of("backup");
        Files.walk(backup)
             .sorted(Comparator.reverseOrder())
             .forEach(path -> {
                 try {
                     Files.delete(path);
                 } catch (IOException ex) {
                     System.out.println("Error: " + ex.getMessage());
                 }
             });
    }
}
