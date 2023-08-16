package com.dev.mcc_tools.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class AppointmentSearchRequest {
    private String firstName;
    private String lastName;
    private String status;
    private String minDate;
    private String maxDate;
    private String location;
    private String invoiceNumber;

    public Timestamp parseDateString(String dateString) throws ParseException {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return toTimestamp(dateString + " 00:00", dt);
    }
    public Timestamp toTimestamp(String dateString , DateTimeFormatter formatter) throws ParseException {
//        DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime ldt = LocalDateTime.parse(dateString, formatter);

        System.out.println("localDateTime : " + ldt);


        return Timestamp.from(ldt.toInstant(ZoneOffset.UTC));
    }
}