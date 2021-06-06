package br.com.guilhermealvessilve.certification.study.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Alves
 */
public class NumberMain {

    public static void main(String[] args) {
        double priceDouble = 142.125125;
        System.out.println(Math.round(priceDouble * 100.0) / 100.0);
        System.out.println(Math.round(priceDouble * 1000.0) / 1000.0);
        System.out.println(Math.round(priceDouble * 10000.0) / 10000.0);
        BigDecimal price = BigDecimal.valueOf(1.85);
        var rate = BigDecimal.valueOf(0.65);
        System.out.println(price.subtract(rate.multiply(rate)));
        System.out.println(price.multiply(rate));
        System.out.println(price.subtract(price.multiply(rate)));
        System.out.println(price.subtract(price.multiply(rate)).setScale(2, RoundingMode.HALF_UP));
        System.out.println(price.subtract(price.multiply(rate)).setScale(2, RoundingMode.HALF_UP));
        System.out.println(price.subtract(price.multiply(rate)).setScale(2, RoundingMode.HALF_DOWN));
        Locale locale = Locale.FRANCE;
        System.out.println(locale);
        
        final var currencyFormat = NumberFormat.getCurrencyInstance(locale);
        final var percentFormat = NumberFormat.getPercentInstance(locale);
        System.out.println(currencyFormat.format(price));
        System.out.println(percentFormat.format(price));
        System.out.println(currencyFormat.format(price));
        System.out.println(percentFormat.format(price));
        
        final var localeUK = Locale.UK;
        final var currencyFormatUK = NumberFormat.getCurrencyInstance(localeUK);
        final var percentFormatUK = NumberFormat.getPercentInstance(localeUK);
       
        System.out.println(currencyFormatUK.format(price));
        System.out.println(percentFormatUK.format(price));
    }
}
