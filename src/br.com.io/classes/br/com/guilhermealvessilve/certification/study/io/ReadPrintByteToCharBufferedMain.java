package br.com.guilhermealvessilve.certification.study.io;

import br.com.guilhermealvessilve.certification.study.utils.ArgsUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 *
 * @author Alves
 */
public class ReadPrintByteToCharBufferedMain {

    private static final Logger LOGGER = Logger.getLogger(InOutBytesMain.class.getName());
    
    public static void main(String[] args) throws IOException {
        
        if (!ArgsUtils.requireMinSize(args, 2)) {
            System.out.println("InOutMain source-path target-path");
            return;
        }
        
        LOGGER.info("Will copy the source to target!");
        
        final Path source = Paths.get(args[0]);
        final Path target = Paths.get(args[1]);
        
        final Charset utf8 = StandardCharsets.UTF_8;
        try (final var buffIn = new BufferedReader(new InputStreamReader(new FileInputStream(source.toFile()), utf8));
             final var buffOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(target.toFile()), utf8))) {
            
            String line;
            while ((line = buffIn.readLine()) != null) {
                buffOut.write(line);
                buffOut.newLine();
            }
        }
        
        LOGGER.info("Success!");
    }
}
