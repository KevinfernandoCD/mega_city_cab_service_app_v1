
<%
    String username = (session != null && session.getAttribute("username") != null) ? (String) session.getAttribute("username") : "Admin";
    String email = (session != null && session.getAttribute("email") != null) ? (String) session.getAttribute("email") : "admin@example.com";
    
    request.setAttribute("username", username);
    request.setAttribute("email", email);


%>