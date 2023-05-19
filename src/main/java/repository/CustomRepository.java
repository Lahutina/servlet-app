package repository;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomRepository {

    public static User addUser(User userAccount) {
        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, password, role) VALUES (?, ?, ?)");
            statement.setString(1, userAccount.getEmail());
            statement.setString(2, userAccount.getPassword());
            statement.setString(3, userAccount.getRole());
            statement.executeUpdate();

            statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, userAccount.getEmail());
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            } else {
                return new User(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                allUsers.add(new User(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public static void deleteUser(Long id) {
        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?;");
            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User findUser(String email, String user_password) {
        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
            statement.setString(1, email);
            statement.setString(2, user_password);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            } else {
                return new User(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User findUser(Long id) {
        try {
            Connection connection = DBConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            } else {
                return new User(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update(User updatedUser) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET email = ?, password = ?, role = ? WHERE id = ?");
            statement.setString(1, updatedUser.getEmail());
            statement.setString(2, updatedUser.getPassword());
            statement.setString(3, updatedUser.getRole());
            statement.setLong(4, updatedUser.getId());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
