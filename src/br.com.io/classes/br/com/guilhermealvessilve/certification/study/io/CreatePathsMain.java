package br.com.guilhermealvessilve.certification.study.io;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

/**
 *
 * @author Alves
 */
public class CreatePathsMain {

    public static void main(String[] args) throws IOException {
        
        final Path source = Path.of("G:/users/joe/docs");
        final Path backup = Path.of("G:/users/joe/backup/docs");
        if (Files.notExists(backup)) {
            Files.createDirectories(backup);
        }
        
        final Path readme = backup.resolve("../readme.txt").normalize();
        Files.createFile(readme);
        Files.writeString(readme, "Backup time: " + Instant.now());
        Files.lines(readme, Charset.forName("UTF-8"))
            .forEach(line -> System.out.println(line));
        
        
    }
}
