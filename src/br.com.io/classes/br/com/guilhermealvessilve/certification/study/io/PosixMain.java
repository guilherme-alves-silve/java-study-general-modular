package br.com.guilhermealvessilve.certification.study.io;

import br.com.guilhermealvessilve.certification.study.utils.ArgsUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 *
 * @author Alves
 */
public class PosixMain {
    
    public static void main(String[] args) throws IOException {
        
        if (!ArgsUtils.requireMinSize(args, 1)) {
            return;
        }
        
        Path path = Paths.get(args[0]);
        Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrw---x");
        Files.setPosixFilePermissions(path, permissions);
    }
}
