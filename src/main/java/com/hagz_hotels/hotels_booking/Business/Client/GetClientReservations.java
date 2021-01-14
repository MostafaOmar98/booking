package com.hagz_hotels.hotels_booking.Business.Client;

import com.hagz_hotels.hotels_booking.Business.DTO.ReservationDTOForClient;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientHotelReviewDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetClientReservations {
    private static final ClientRoomReservationDAO clientRoomReservationDAO = new ClientRoomReservationDAO();
    private static final ClientHotelReviewDAO clientHotelReviewDAO = new ClientHotelReviewDAO();

    public static List<ReservationDTOForClient> execute(Integer clientId) throws SQLException, ClassNotFoundException {
        List<ClientRoomReservation> reservations = clientRoomReservationDAO.findAllByClientId(clientId);
        List<ReservationDTOForClient> reservationDTOs = new ArrayList<>();

        for (ClientRoomReservation r : reservations) {
            ReservationDTOForClient d = new ReservationDTOForClient();
            d.setCheckIn(r.getCheckIn());
            d.setCheckOut(r.getCheckOut());
            d.setStatus(r.getStatusName());
            d.setCreatedAt(r.getCreatedAt());
            d.setReservationId(r.getReservationId());
            d.setTotalPrice(r.getTotalPrice());

            d.setRoomId(r.getRoomId());

            d.setReviewable(!clientHotelReviewDAO.has(r.getReservationId()) && r.getStatusName().equals("CHECKED_OUT"));

            reservationDTOs.add(d);
        }
        return reservationDTOs;
    }
}
