package com.mycompany.megacity_cab_service.dao;

import com.mycompany.megacity_cab_service.models.User;
import com.mycompany.megacity_cab_service.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing user-related database operations.
 */
public class UserDAO {

    private final DbConnection dbConnection;

    public UserDAO() throws SQLException, ClassNotFoundException {
        this.dbConnection = DbConnection.getInstance();
    }

    /**
     * Inserts a new user into the database.
     *
     * @param user The user to insert.
     * @return true if successful, false otherwise.
     */
    public String insertUser(User user) {
        String query = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
        try {
            dbConnection.executeWriteQuery(query, user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
            return "User has been added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
     public String insertDummyAdmin() {
        String query = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
        try {
            dbConnection.executeWriteQuery(query, "admin", "admin@gmail.com", "123", "admin");
            return "User has been added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates a user's details.
     *
     * @param user The user object with updated values.
     * @return true if successful, false otherwise.
     */
    public boolean updateUser(User user) {
        String query = "UPDATE users SET username = ?, email = ?, password = ?, role = ? WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(query, user.getUsername(), user.getEmail(), user.getPassword(), user.getRole(), user.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a user from the database.
     *
     * @param userId The user ID to delete.
     * @return true if successful, false otherwise.
     */
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            dbConnection.executeWriteQuery(query, userId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email The email to search for.
     * @return A User object if found, otherwise null.
     */
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The user ID.
     * @return A User object if found, otherwise null.
     */
    public User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of User objects.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(extractUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Authenticates a user by their email and password.
     *
     * @param email    The email of the user.
     * @param password The password to authenticate.
     * @return The User object if authentication is successful, otherwise null.
     */
    public User authenticateUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = dbConnection.getConnection().prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractUserFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Extracts a User object from a ResultSet.
     *
     * @param rs The ResultSet containing user data.
     * @return A User object.
     * @throws SQLException If a database access error occurs.
     */
    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("role")
        ) {};
    }
}
