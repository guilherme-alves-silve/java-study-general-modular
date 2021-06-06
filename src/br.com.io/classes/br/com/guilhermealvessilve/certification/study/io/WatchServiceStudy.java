package br.com.guilhermealvessilve.certification.study.io;

import br.com.guilhermealvessilve.certification.study.utils.ArgsUtils;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Alves Silveira
 */
public class WatchServiceStudy {
    
    private static final Logger LOGGER = Logger.getLogger(WatchServiceStudy.class.getName());
    
    public static void main(String[] args) {
        
        if (!ArgsUtils.requireMinSize(args, 1)) {
            System.out.println("Inform the path to be watched!");
            return;
        }
        
        final var location = args[0];
        
        LOGGER.info("The Watcher started!");
        
        try (WatchService watcher = FileSystems.getDefault().newWatchService()) {
            Path path = Paths.get(location);
            path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
            
            while (true) {
                WatchKey watchKey = null;
                try {
                    watchKey = watcher.take();
                    for (final WatchEvent event : watchKey.pollEvents()) {
                        LOGGER.info(String.format("Count %d, Kind %s, Context: %s",
                                event.count(),
                                event.kind(),
                                event.context()
                        ));
                    }
                } catch (InterruptedException ex) {
                    LOGGER.warning(String.format("The thread %s was interrupted", ex.getMessage()));
                    return;
                } finally {
                    if (watchKey != null) {
                        watchKey.reset();
                    }
                }
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
