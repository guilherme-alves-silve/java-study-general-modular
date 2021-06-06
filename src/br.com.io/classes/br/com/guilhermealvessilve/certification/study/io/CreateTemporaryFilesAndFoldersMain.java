package br.com.guilhermealvessilve.certification.study.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Alves
 */
public class CreateTemporaryFilesAndFoldersMain {
    
    public static void main(String[] args) throws IOException {
        final Path path1 = Files.createTempDirectory("TEMP");
        final Path path2 = Files.createTempFile(path1, "TEMP", ".tmp");
        Files.deleteIfExists(path2);
        Files.deleteIfExists(path1);
    }
}
