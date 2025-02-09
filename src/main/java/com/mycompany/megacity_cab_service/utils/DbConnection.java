package com.mycompany.megacity_cab_service.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Utility class to manage database connections using Singleton pattern.
 */
public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/megacity";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static DbConnection instance;
    private Connection connection;

    /**
     * Private constructor to prevent instantiation.
     */
    private DbConnection() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Error connecting to the database", e);
        }
    }

    /**
     * Provides the singleton instance of DbConnection.
     *
     * @return the singleton instance
     * @throws SQLException if a database access error occurs
     */
    public static DbConnection getInstance() throws SQLException {
        if (instance == null) {
            synchronized (DbConnection.class) {
                if (instance == null) {
                    instance = new DbConnection();
                }
            }
        }
        return instance;
    }

    /**
     * Returns the active database connection.
     *
     * @return the Connection object
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Executes a write (INSERT, UPDATE, DELETE) query with parameters.
     *
     * @param query  the SQL query to execute
     * @param params the parameters for the query
     * @throws SQLException if a database access error occurs
     */
    public void executeWriteQuery(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
        }
    }

    /**
     * Closes the database connection.
     */
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                instance = null; // Reset the instance for future use
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
