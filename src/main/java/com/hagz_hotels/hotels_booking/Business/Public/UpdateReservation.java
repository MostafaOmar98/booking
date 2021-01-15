package com.hagz_hotels.hotels_booking.Business.Public;

import com.hagz_hotels.hotels_booking.Model.DAO.ClientRoomReservationDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.HotelDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.RoomDAO;
import com.hagz_hotels.hotels_booking.Model.DAO.UserDAO;
import com.hagz_hotels.hotels_booking.Model.Entities.ClientRoomReservation;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;
import com.hagz_hotels.hotels_booking.Model.Entities.Room;
import com.hagz_hotels.hotels_booking.Model.Entities.User;
import com.hagz_hotels.hotels_booking.Util.JsonResponse;
import com.hagz_hotels.hotels_booking.Util.MailUtil;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

public class UpdateReservation {
    private static final ClientRoomReservationDAO clientRoomReservationDAO = new ClientRoomReservationDAO();
    private static final UserDAO userDAO = new UserDAO();
    private static final RoomDAO roomDAO = new RoomDAO();
    private static final HotelDAO hotelDAO = new HotelDAO();

    public static void execute(Integer reservationId, String state) throws SQLException, ClassNotFoundException, GeneralSecurityException, MessagingException {
        ClientRoomReservation reservation = clientRoomReservationDAO.findById(reservationId);
        JsonResponse jsonResponse = new JsonResponse();
        if (state.equals("CANCELED") && (reservation.getStatusName().equals("CHECKED_IN") || reservation.getStatusName().equals("CHECKED_OUT")))
            throw new IllegalArgumentException("Invalid new state");
        else if (state.equals("CONFIRMED") && !reservation.getStatusName().equals("PENDING"))
            throw new IllegalArgumentException("Invalid new state");
        else if (state.equals("CHECKED_IN") && !reservation.getStatusName().equals("CONFIRMED"))
            throw new IllegalArgumentException("Invalid new state");
        else if (state.equals("CHECKED_OUT") && !reservation.getStatusName().equals("CHECKED_IN"))
            throw new IllegalArgumentException("Invalid new state");
        else {
            clientRoomReservationDAO.updateStatus(reservationId, state);
            if (state.equals("CANCELED")) {
                User client = userDAO.findById(reservation.getClientId());
                Room room = roomDAO.findById(reservation.getRoomId());
                Hotel hotel = hotelDAO.findById(room.getHotelId());
                User admin = userDAO.findById(hotel.getAdminId());
                MailUtil.sendMail(client.getEmail(), "[Booking App] A recent reservation of yours has been canceled",
                        "Your reservation for room " + room.getRoomId() + " Has been canceled\n" +
                                "Contact the hotel for more info\n" +
                                "Hotel Name: " + hotel.getName() + "\n" +
                                "Hotel Phone: " + hotel.getPhone() + "\n");
                MailUtil.sendMail(admin.getEmail(), "[Booking App] A recent reservation of your hotel has been canceled",
                        "Reservation for room " + room.getRoomId() + " Has been canceled\n" +
                                "Client Name: " + client.getUsername() + "\n" +
                                "Client Phone: " + client.getPhone() + "\n" +
                                "Reservation Id: " + reservationId);
            }
        }
    }
}

