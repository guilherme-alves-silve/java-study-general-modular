package br.com.guilhermealvessilve.certification.study.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Alves
 */
public class NavigatingFileSystemMain {

    public static void main(String[] args) throws IOException {
        Path joe = Path.of("G:/", "users", "joe");
        Path p1 = Path.of("G:/", "users", "joe", "docs", "some.txt");
        for (int i = 0; i < p1.getNameCount(); i++) {
            Path p = p1.getName(i);
            System.out.println(p);
        }
        
        Path p2 = Path.of("./pics/s.txt");
        Files.createSymbolicLink(p2, p1);
        Files.list(joe).forEach(p -> System.out.println(p));
        Files.walk(joe)
            .map(p -> p.toString())
            .filter(s -> s.endsWith("txt"))
            .forEach(p -> System.out.println(p));
        Path p3 = Files.readSymbolicLink(p2);
        System.out.println(p3);
    }
}
