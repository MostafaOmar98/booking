package com.hagz_hotels.hotels_booking.Business.Client;

import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.Util;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReserveRoom {
    private static final RoomDAO roomDAO = new RoomDAO();
    private static final ClientRoomReservationDAO clientRoomReservationDAO = new ClientRoomReservationDAO();

    public static void execute(Integer clientId, Integer roomId, LocalDate checkIn, LocalDate checkOut) throws SQLException, ClassNotFoundException {
        if (!roomDAO.isAvailable(roomId, checkIn, checkOut))
            throw new IllegalArgumentException("Room id " + roomId + " is not available between " + checkIn + " and " + checkOut);

        Float pricePerNight = roomDAO.findById(roomId).getPricePerNight();
        long nights = Util.getNights(checkIn, checkOut);
        if (nights <= 0)
            throw new IllegalArgumentException("Number of nights must be strictly positive");

        clientRoomReservationDAO.create(clientId, roomId, checkIn, checkOut, pricePerNight * nights);
    }
}
