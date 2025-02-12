package com.mycompany.megacity_cab_service.controllers;

import com.mycompany.megacity_cab_service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        
        try {
            this.authService = new AuthService();
        } catch (SQLException ex) {
            Logger.getLogger(AuthServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AuthServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * Handles user login.
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        String result = authService.login(email, password, session);
        response.setStatus("Login successful".equals(result) ? HttpServletResponse.SC_OK : HttpServletResponse.SC_UNAUTHORIZED);
        
        try (PrintWriter out = response.getWriter()) {
            out.write("{\"message\": \"" + result + "\"}");
        }
    }

    /**
     * Simple GET endpoint to check if the API is running.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.write("Hello");
        }
    }
    
      @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Don't create a new session
        if (session != null) {
            session.invalidate(); // Destroy session
        }

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        try (PrintWriter out = response.getWriter()) {
            out.write("{\"message\": \"Logout successful\"}");
        }
    }
}
