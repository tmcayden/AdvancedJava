package edu.wgu.d387_sample_code.model.response;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

@RestController
public class PresentationResponse {

    @GetMapping("/time")
    @CrossOrigin
    public String GetTimeZones() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh':'mm a z");
        String message = "";
        ZoneId zMountain = ZoneId.of("US/Mountain");
        ZoneId zEastern = ZoneId.of("US/Eastern");
        ZoneId zUTC = ZoneId.of("UTC");
        ZoneId zoneId = ZoneId.systemDefault();

        LocalDateTime localDateTime = LocalDateTime.of(2024, 01, 05, 12, 00);
        ZonedDateTime zonedDateTime=localDateTime.atZone(zoneId);

        ZonedDateTime zonedDateTimeMountain=zonedDateTime.withZoneSameInstant(zMountain);

        ZonedDateTime zonedDateTimeUtc=zonedDateTime.withZoneSameInstant(zUTC);



        ZonedDateTime zonedDateTimeEastern=zonedDateTime.withZoneSameInstant(zEastern);
        message += zonedDateTimeEastern.format(formatter);
        message += " | " + zonedDateTimeMountain.format(formatter);
        message += " | " + zonedDateTimeUtc.format(formatter);


        return message;
    }
}
