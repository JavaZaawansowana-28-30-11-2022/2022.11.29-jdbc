package com.comarch.szkolenia.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
    public static Connection connection = null;
    public static void main(String[] args) throws SQLException {
        connect();
        User user = new User(1, "asdfasd", "admin123");
        //persistUser2(user);
        //deleteUser(1);

        //System.out.println(getUserById(9).get());
        System.out.println(getAllUsers());
    }

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test7", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void persistUser(User user) throws SQLException {
        String sql = new StringBuilder("INSERT INTO tuser (login, password) VALUES (")
                .append("'")
                .append(user.getLogin())
                .append("', '")
                .append(user.getPassword())
                .append("');").toString();

        Statement statement = connection.createStatement();
        statement.execute(sql);
    }

    public static void persistUser2(User user) throws SQLException {
        String sql = "INSERT INTO tuser (login, password) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());

        ps.executeUpdate();
    }

    public static void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM tuser WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public static Optional<User> getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM tuser WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            return Optional.of(new User(
                    rs.getInt("id"),
                    rs.getString("login"),
                    rs.getString("password")));
        }
        return Optional.empty();
    }

    public static List<User> getAllUsers() throws SQLException {
        List<User> result = new ArrayList<>();
        String sql = "SELECT * FROM tuser";
        PreparedStatement ps = connection.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            result.add(new User(
                    rs.getInt("id"),
                    rs.getString("login"),
                    rs.getString("password")));
        }

        return result;
    }
}
