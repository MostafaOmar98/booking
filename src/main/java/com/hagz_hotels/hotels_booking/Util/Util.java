package com.hagz_hotels.hotels_booking.Util;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Util {
    public static long getNights(LocalDate checkIn, LocalDate checkOut) {
        return DAYS.between(checkIn, checkOut);
    }
}
