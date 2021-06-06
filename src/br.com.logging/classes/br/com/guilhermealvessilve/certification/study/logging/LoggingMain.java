package br.com.guilhermealvessilve.certification.study.logging;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Alves
 */
public class LoggingMain {
    
    private static final Logger LOGGER = Logger.getLogger(LoggingMain.class.getName());
        
    public static void main(String[] args) {
        
        LOGGER.logp(Level.FINEST, "LoggingMain", "main", "Starting main...");
        
        try {
            execute();
        } catch (NoSuchFileException ex) {
            MessageFormat formatter = new MessageFormat("Error: {0}");
            LOGGER.severe(formatter.format(new Object[] { ex.getMessage() }));
        } catch (IOException ex) {
            var formatter = new MessageFormat("Error: {0}");
            LOGGER.severe(() -> formatter.format(ex.getMessage()));
        } catch (Exception ex) {
            LOGGER.severe(ex.getMessage());
        }
        
        LOGGER.exiting("LoggingMain", "execute");
    }
    
    private static void execute() throws NoSuchFileException, IOException {
    
        LOGGER.entering("LoggingMain", "execute");
        
        LOGGER.finest("Everything okay");
        LOGGER.finer("Very good");
        LOGGER.fine("Good");
        LOGGER.config("Where gonna config");
        
        ResourceBundle bundle = ResourceBundle.getBundle("messages");
        LOGGER.info(bundle.getString("printConfig"));
        
        LOGGER.warning(() -> "Will throw an exception");
        
        try {
            throw new IOException(new NoSuchFileException("The file was not found"));
        } catch (IOException ex) {
            LOGGER.logrb(Level.SEVERE, bundle, "exception", ex);
            LOGGER.throwing("LoggingMain", "execute", ex.getCause());
            throw (NoSuchFileException) ex.getCause();
        }
    }
}
