package br.com.guilhermealvessilve.certification.study.io;

import br.com.guilhermealvessilve.certification.study.utils.ArgsUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 *
 * @author Alves
 */
public class InOutBytesMain {

    private static final Logger LOGGER = Logger.getLogger(InOutBytesMain.class.getName());
    
    public static void main(String[] args) throws IOException {
        
        if (!ArgsUtils.requireMinSize(args, 2)) {
            System.out.println("InOutMain source-path target-path");
            return;
        }
        
        LOGGER.info("Will copy the source to target!");
        
        final Path source = Paths.get(args[0]);
        final Path target = Paths.get(args[1]);
        
        try (final InputStream in = new FileInputStream(source.toFile());
             final OutputStream out = new FileOutputStream(target.toFile())) {
            
            final var buffer = new byte[1024];
            
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        }
        
        LOGGER.info("Success!");
    }
}
