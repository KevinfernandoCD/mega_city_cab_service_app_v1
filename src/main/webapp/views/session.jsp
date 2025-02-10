<%
     if (session == null) {
        session = request.getSession(true);
    }
     session.setAttribute("username", "TestUser");
        session.setAttribute("email", "testuser@example.com");
        session.setAttribute("id", "12345"); // Sample User ID
    // Check if a user session exists
    if (session == null || session.getAttribute("username") == null || session.getAttribute("email") == null || session.getAttribute("id") == null) {
        response.sendRedirect("login.jsp"); // Redirect to login page
        return; // Stop further execution
    }

    // Retrieve user details from session
    String username = (String) session.getAttribute("username");
    String email = (String) session.getAttribute("email");
    String userId = (String) session.getAttribute("id");

    // Store details in request scope
    request.setAttribute("username", username);
    request.setAttribute("email", email);
    request.setAttribute("id", userId);
%>
