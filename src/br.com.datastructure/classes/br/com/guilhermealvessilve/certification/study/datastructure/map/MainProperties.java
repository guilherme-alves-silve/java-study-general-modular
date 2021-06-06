package br.com.guilhermealvessilve.certification.study.datastructure.map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

/**
 *
 * @author Alves
 */
public class MainProperties {

    public static void main(String[] args) throws IOException {
        final var path = "D:\\Java_Practice\\example.properties";
        try (final var in = new FileInputStream(path);
             final var out = new FileOutputStream(path)) {
            Properties prop = new Properties();
            prop.load(in);
            prop.list(System.out);
            prop.setProperty("name", "John Wick"); 
            System.out.println(prop.get("name"));
            prop.store(out, "Last updated at:  " + LocalDate.now() + " " + LocalTime.now());
        }
    }
}
