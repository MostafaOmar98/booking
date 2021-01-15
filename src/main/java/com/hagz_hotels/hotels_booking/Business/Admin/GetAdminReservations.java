package com.hagz_hotels.hotels_booking.Business.Admin;

import com.hagz_hotels.hotels_booking.Business.DTO.ReservationDTOForAdmin;
import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;
import com.hagz_hotels.hotels_booking.Model.Entities.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetAdminReservations {
    private static final ClientRoomReservationDAO clientRoomReservationDAO = new ClientRoomReservationDAO();
    private static final UserDAO userDAO = new UserDAO();

    public static List<ReservationDTOForAdmin> execute(Integer hotelId) throws SQLException, ClassNotFoundException {
        List<ClientRoomReservation> clientRoomReservations = clientRoomReservationDAO.findAllByHotelId(hotelId);
        List<ReservationDTOForAdmin> reservations = new ArrayList<>();
        for (ClientRoomReservation cr : clientRoomReservations) {
            ReservationDTOForAdmin r = new ReservationDTOForAdmin();
            User user = userDAO.findById(cr.getClientId());
            r.setReservationId(cr.getReservationId());
            r.setClientId(user.getUserId());
            r.setClientEmail(user.getEmail());
            r.setClientName(user.getUsername());
            r.setClientPhone(user.getPhone());
            r.setRoomId(cr.getRoomId());
            r.setStatus(cr.getStatusName());
            r.setCheckIn(cr.getCheckIn());
            r.setCheckOut(cr.getCheckOut());
            r.setCreatedAt(cr.getCreatedAt());
            reservations.add(r);
        }
        return reservations;
    }
}
