package br.com.guilhermealvessilve.certification.study.resourcebundle;

import java.text.MessageFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Alves
 */
public class ResourceBundleMain {

    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        
        useResourceBundle();
        useResourceBundle(new Locale("pt", "BR"));
    }
    
    private static void useResourceBundle() {
        useResourceBundle(null);
    }
    
    private static void useResourceBundle(final Locale locale) {
        final var resourceBundle = (null == locale)
                ? ResourceBundle.getBundle("messages")
                : ResourceBundle.getBundle("messages", locale);
        
        final var dateFormatPattern = resourceBundle.getString("dateFormat");
        final var dateTimeFormatter = (null == locale) 
                ? DateTimeFormatter.ofPattern(dateFormatPattern)
                : DateTimeFormatter.ofPattern(dateFormatPattern, locale);
        
        final var offerPattern = resourceBundle.getString("offer");
        System.out.println(MessageFormat.format(offerPattern, "Green Tea", "$ 25.10", "$ 10.50", dateTimeFormatter.format(ZonedDateTime.now())));
    }
}
