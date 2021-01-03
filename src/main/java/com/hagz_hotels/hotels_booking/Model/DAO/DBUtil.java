package com.hagz_hotels.hotels_booking.Model.DAO;
import com.hagz_hotels.hotels_booking.Model.Entities.Hotel;

import java.sql.*;
import java.util.function.Function;

public class DBUtil {
    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelsBooking?useSSL=false", "root", "123456");
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("Connection Failed");
            throwables.printStackTrace();
        }
        return con;
    }

    public static void close(Connection con, PreparedStatement stmt, ResultSet set) {
        try {
            if (set != null)
                set.close();
            if (stmt != null)
                stmt.close();
            if (con != null)
                con.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(Connection con, PreparedStatement stmt) {
        close (con, stmt, null);
    }

    public static void executeUpdate(String query, Object... args) throws SQLException {
        Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        for (int i = 0; i < args.length; ++i) {
            stmt.setObject(i + 1, args[i]);
        }
        stmt.executeUpdate();
        close(con, stmt);
    }

    public static <T> T selectOne(String query, Function<ResultSet, T> map, Object... args) {
        Connection con = DBUtil.getConnection();
        T ret = null;
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            for (int i = 0; i < args.length; ++i)
                stmt.setObject(i + 1, args[i]);

            ResultSet set = stmt.executeQuery();
            if (set.next())
                ret = map.apply(set);
            DBUtil.close(con, stmt, set);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ret;
    }
}
