package br.com.guilhermealvessilve.certification.study.io;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

/**
 *
 * @author Alves
 */
public class FileSystemMain {

    public static void main(String[] args) {
        
        final FileSystem fileSystem = FileSystems.getDefault();
        
        fileSystem.getFileStores()
                .forEach(fileStore -> System.out.printf("File Store: %s %s%n", fileStore.type(), fileStore.name()));
        
        fileSystem.getRootDirectories()
                .forEach(path -> System.out.printf("Root: %s%n", path));
        
        System.out.printf("Path Separator: %s%n", fileSystem.getSeparator());
    }
}
