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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Alves Silveira
 */
public class WatchServiceStudyTimeout {

    private static final Logger LOGGER = Logger.getLogger(WatchServiceStudy.class.getName());

    public static void main(String[] args) {

        if (!ArgsUtils.requireMinSize(args, 1)) {
            System.out.println("Inform the path to be watched!");
            return;
        }

        final var location = args[0];

        LOGGER.info("The Watcher started!");

        Path path = Paths.get(location);
        try ( WatchService watcher = FileSystems.getDefault().newWatchService()) {
            path.register(
                    watcher,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY
            );

            while (true) {
                WatchKey key = null;
                try {
                    key = watcher.poll(5, TimeUnit.SECONDS);
                    if (null == key) {
                        continue;
                    }

                    for (WatchEvent event : key.pollEvents()) {
                        WatchEvent.Kind kind = event.kind();
                        System.out.println("Kind Name: " + kind.name());
                        System.out.println("Kind Type: " + kind.type());
                        System.out.println("Event Context: " + event.context());
                        System.out.println("Event Count: " + event.count());
                    }

                } catch (InterruptedException ex) {
                    LOGGER.warning(String.format("The thread %s was interrupted", ex.getMessage()));
                    return;
                } finally {
                    if (key != null) {
                        key.reset();
                    }
                }
            }

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
