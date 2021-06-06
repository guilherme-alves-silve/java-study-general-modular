package br.com.guilhermealvessilve.certification.study.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 *
 * @author Alves
 */
public class DateTimeMain {
    
    public static void main(String[] args) {
        var date = LocalDate.now();
        var time = LocalTime.now();
        var dateTime = LocalDateTime.now();
        System.out.println(date);
        System.out.println(time);
        System.out.println(dateTime);
        System.out.println(date.plusYears(1).getDayOfWeek());
        System.out.println(date.getDayOfWeek());
        var teaTime = LocalTime.of(17, 30);
        System.out.println(Duration.between(LocalTime.now(), teaTime));
        System.out.println(Duration.between(teaTime, LocalTime.now()));
        System.out.println(Duration.between(teaTime, LocalTime.now()).toMinutesPart());
        System.out.println(Duration.between(teaTime, LocalTime.now()).toHoursPart());
        System.out.println(Duration.between(teaTime, LocalTime.now()).toMinutes());
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now().plusDays(1));
        var tomorrow = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.now());
        System.out.println(tomorrow);
        ZoneId katmandu = ZoneId.of("Asia/Katmandu");
        var london = ZoneId.of("Europe/London");
        ZonedDateTime londonDate = ZonedDateTime.of(LocalDateTime.now(), london);
        System.out.println(londonDate.withZoneSameInstant(katmandu));
        System.out.println(londonDate.withZoneSameInstant(katmandu));
        ZonedDateTime katmanduDate = londonDate.withZoneSameInstant(katmandu);
        System.out.println(katmanduDate.getOffset());
        System.out.println(londonDate.getOffset());
        System.out.println(londonDate);
        System.out.println(londonDate.getOffset());
        System.out.println(katmanduDate.getOffset());
        String pattern = "EE', 'd' of 'MMM yyyy' at 'HH:mm z";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
        System.out.println(formatter.format(londonDate));
        System.out.println(formatter.format(katmanduDate));
        System.out.println(Instant.now());
        var offsetDateTime = OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.ofHours(2));
        System.out.println("offsetDateTime: " + offsetDateTime);
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.UK)));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withZone(ZoneId.of("America/Sao_Paulo"))));
        System.out.println(LocalDateTime.now().with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.println(TemporalAdjusters.lastDayOfYear().adjustInto(LocalDateTime.now()));
        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}
