package br.com.guilhermealvessilve.certification.study.io;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Alves
 */
public class HandleZipArchivesMain {
    
    public static void main(String[] args) throws IOException {
        final Path source = Path.of(".");
        final Path zip = Path.of("joe.zip");
        Files.createFile(zip);
        
        try (final var out = new ZipOutputStream(Files.newOutputStream(zip))) {
            out.setLevel(Deflater.DEFAULT_COMPRESSION);
            Files.walk(source)
                .filter(p -> !Files.isDirectory(p))
                .forEach(path -> {
                    final var zipEntry = new ZipEntry(source.relativize(path).toString());
                    try {
                        out.putNextEntry(zipEntry);
                        out.write(Files.readAllBytes(path));
                        out.closeEntry();
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                });
        }
        
        final Path zip2 = Path.of("joe2.zip");
        Files.createFile(zip2);
        try (final FileSystem fileSystem = FileSystems.newFileSystem(zip2, (ClassLoader) null)) {
            Files.walk(source)
                .forEach(path -> {
                    try {
                        Path target = fileSystem.getPath("/" + path.toString());
                        Files.copy(source, target);
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                });
        }
    }
}
