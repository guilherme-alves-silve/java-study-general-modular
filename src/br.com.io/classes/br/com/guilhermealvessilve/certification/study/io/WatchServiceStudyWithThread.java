package br.com.guilhermealvessilve.certification.study.io;

import br.com.guilhermealvessilve.certification.study.utils.ArgsUtils;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme Alves Silveira
 */
public class WatchServiceStudyWithThread {

    private static final int CREATE_RANDOM_FILE = 1;
    private static final int STOP_PROGRAM = 2;

    private static final Logger LOGGER = Logger.getLogger(WatchServiceStudyWithThread.class.getName());

    public static void main(String[] args) {

        if (!ArgsUtils.requireMinSize(args, 1)) {
            System.out.println("Inform the path to be watched!");
            return;
        }

        final var location = args[0];

        final var path = Paths.get(location);
        
        LOGGER.info("The Watcher started!");

        final var watchableThread = new Thread(() -> {
            try ( WatchService watcher = FileSystems.getDefault().newWatchService()) {
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
                        LOGGER.warning(String.format("The thread %s was interrupted", Thread.currentThread().getName()));
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
        }, "WatchableThread");

        watchableThread.start();

        printInstructions(path);
        try ( Scanner scanner = new Scanner(System.in)) {

            final var random = new Random();

            OUTER_LOOP:
            while (true) {

                String output = scanner.next().toLowerCase().trim();
                if (output.isBlank() || !isDigits(output)) {
                    printCorrectionMessage(path);
                    continue;
                }

                int value = Integer.parseInt(output);
                switch (value) {
                    case CREATE_RANDOM_FILE:
                        Path newFile;
                        do {
                            newFile = Paths.get(random.nextLong() + ".txt");
                        } while (Files.exists(newFile));

                        try {
                            Files.createFile(path.resolve(newFile));
                        } catch (IOException ex) {
                            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
                        }
                        break;
                    case STOP_PROGRAM:
                        watchableThread.interrupt();
                        break OUTER_LOOP;
                    default:
                        printCorrectionMessage(path);
                }
            }
        }

    }

    private static void printInstructions(final Path path) {
        System.out.println("1 - Create random file name in the path " + path
                + "\n2 - Stop the program");
    }

    private static void printCorrectionMessage(final Path path) {
        System.out.println("Invalid, follow the instructions below: ");
        printInstructions(path);
    }

    private static boolean isDigits(final String value) {
        return value.chars()
                .allMatch(ch -> Character.isDigit((char) ch));
    }
}
