package com.hagz_hotels.hotels_booking.Model.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DBUtil {
    public final static String OVERLAPPING_RESERVATION = "NOT (Status = 'CHECKED_OUT' OR " +
            "Status = 'CACNELED' OR " +
            "? >= CheckOut OR CheckIn >= ?) "; // (?, ?) should be (checkIn, checkOut) in order.

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = null;
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(conf.url, conf.user, conf.password);
        return con;
    }

    private static void close(Connection con, PreparedStatement stmt, ResultSet set) throws SQLException {
        if (set != null)
            set.close();
        if (stmt != null)
            stmt.close();
        if (con != null)
            con.close();
    }

    private static void close(Connection con, PreparedStatement stmt) throws SQLException {
        close(con, stmt, null);
    }

    public static void executeUpdate(String query, Object... args) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        stmt = con.prepareStatement(query);
        for (int i = 0; i < args.length; ++i) {
            stmt.setObject(i + 1, args[i]);
        }
        stmt.executeUpdate();
        close(con, stmt);
    }

    // returns generated key
    public static Integer insert(String query, Object... args) throws SQLException, ClassNotFoundException {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        Integer key = null;
        stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < args.length; ++i) {
            stmt.setObject(i + 1, args[i]);
        }
        stmt.executeUpdate();
        ResultSet set = stmt.getGeneratedKeys();
        if (set.next())
            key = set.getInt(1);
        close(con, stmt, set);
        return key;
    }

    public static <T> T selectOne(String query, IMapper<ResultSet, T> map, Object... args) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getConnection();
        T ret = null;
        PreparedStatement stmt = con.prepareStatement(query);
        for (int i = 0; i < args.length; ++i)
            stmt.setObject(i + 1, args[i]);

        ResultSet set = stmt.executeQuery();
        if (set.next())
            ret = map.apply(set);
        DBUtil.close(con, stmt, set);
        return ret;
    }

    public static <T> List<T> selectAll(String query, IMapper<ResultSet, T> map, Object... args) throws SQLException, ClassNotFoundException {
        Connection con = DBUtil.getConnection();
        List<T> ret = new ArrayList<>();
        PreparedStatement stmt = con.prepareStatement(query);
        for (int i = 0; i < args.length; ++i)
            stmt.setObject(i + 1, args[i]);

        ResultSet set = stmt.executeQuery();
        while (set.next())
            ret.add(map.apply(set));
        DBUtil.close(con, stmt, set);
        return ret;
    }
}
