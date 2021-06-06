package br.com.guilhermealvessilve.certification.study.io;

import br.com.guilhermealvessilve.certification.study.utils.ArgsUtils;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 *
 * @author Alves
 */
public class ReadPrintCharsMain {

    private static final Logger LOGGER = Logger.getLogger(InOutBytesMain.class.getName());
    
    public static void main(String[] args) throws IOException {
        
        if (!ArgsUtils.requireMinSize(args, 2)) {
            System.out.println("InOutMain source-path target-path");
            return;
        }
        
        LOGGER.info("Will copy the source to target!");
        
        final Path source = Paths.get(args[0]);
        final Path target = Paths.get(args[1]);
        
        try (final Reader in = new FileReader(source.toFile());
             final Writer out = new FileWriter(target.toFile())) {
            
            final var buffer = new char[1024];
            
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        }
        
        LOGGER.info("Success!");
    }
}
