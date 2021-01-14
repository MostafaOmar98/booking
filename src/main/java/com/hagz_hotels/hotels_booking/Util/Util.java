package com.hagz_hotels.hotels_booking.Util;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Util {
    public static long getNights(LocalDate checkIn, LocalDate checkOut) {
        return DAYS.between(checkIn, checkOut);
    }

    public static Float sphericalDistance(Float lng1, Float lat1, Float lng2, Float lat2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lng2 - lng1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        return (float)distance;
    }
}
