<%
    
    // Check if a user session exists
    if (session.getAttribute("username") == null || session.getAttribute("email") == null || session.getAttribute("userId") == null) {
        response.sendRedirect("login.jsp"); // Redirect to login page
        return; // Stop further execution
    }

    // Retrieve user details from session
    String username = (String) session.getAttribute("username");
    String email = (String) session.getAttribute("email");
    String userId = (String) session.getAttribute("id");
    String role = (String) session.getAttribute("role"); 
    // Store details in request scope
    request.setAttribute("username", username);
    request.setAttribute("email", email);
    request.setAttribute("id", userId);
    request.setAttribute("role", role);
%>
