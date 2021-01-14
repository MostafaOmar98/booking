package com.hagz_hotels.hotels_booking.Model.DAO;

import java.sql.SQLException;

public interface IMapper<T, R> {
    R apply(T set) throws SQLException;
}
