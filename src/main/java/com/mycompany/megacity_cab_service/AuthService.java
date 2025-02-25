package com.mycompany.megacity_cab_service;
import com.mycompany.megacity_cab_service.dao.UserDAO;
import com.mycompany.megacity_cab_service.models.User;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;


/**
 * AuthService class that handles authentication logic.
 */
public class AuthService {

    private final UserDAO userDAO;

    public AuthService() throws SQLException, ClassNotFoundException {
        this.userDAO = new UserDAO();
    }

    /**
     * Authenticates the user and creates a session if successful.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @param session  The HttpSession to store user details if authenticated.
     * @return A string message indicating the result of the login attempt.
     */
    public String login(String email, String password, HttpSession session) {
        User user = userDAO.authenticateUser(email, password);
        if (user != null) {
            // Create a session with user details
            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("role", user.getRole());

            return "Login successful";
        }
        return "Invalid email or password";
    }
    
     public String registerDummyAdmin () {
         
        User existingUser = userDAO.getUserByEmail("admin@gmail.com");
        if (existingUser != null) {
            return "Admin account exists.";
        }
        return userDAO.insertDummyAdmin();
    }
}
