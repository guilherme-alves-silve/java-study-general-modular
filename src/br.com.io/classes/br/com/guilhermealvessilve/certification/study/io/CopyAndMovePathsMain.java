package br.com.guilhermealvessilve.certification.study.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alves
 */
public class CopyAndMovePathsMain {
    
    private static final Logger LOGGER = Logger.getLogger(CopyAndMovePathsMain.class.getName());
    
    public static void main(String[] args) throws IOException {
        final Path source = Path.of("docs");
        final Path backup = Path.of("backup");
        final Path archive = Path.of("/archive");
        Files.list(source)
            .forEach(file -> {
                try {
                    Files.copy(file, backup.resolve(file),
                            StandardCopyOption.COPY_ATTRIBUTES,
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, "Error copying file", ex);
                }
            });
        
        Files.move(backup, archive, StandardCopyOption.COPY_ATTRIBUTES,
                                    StandardCopyOption.REPLACE_EXISTING,
                                    StandardCopyOption.ATOMIC_MOVE);
    }
}
