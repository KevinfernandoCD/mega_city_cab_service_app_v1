<%
    // Check if a session exists and user is already logged in
    if (session != null && session.getAttribute("username") != null) {
        response.sendRedirect("admin-dashboard.jsp");
        return; // Stop further execution
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Portal</title>
    <link rel="stylesheet" href="../styles/login.css">
</head>
<body>
    <div class="login-wrapper">
        <div class="login-image">
            <img src="../assets/logo.jpg" alt="Admin Portal">
        </div>
        <div class="login-form">
            <h1>Megacity Cab Service</h1>
            <h3>Admin Portal</h3>
            <form id="loginForm">
                <input type="email" placeholder="Enter email address" id="email" name="email" required>
                
                <input type="password" placeholder="Enter password" id="password" name="password" required>
                
                <button type="submit">Login</button>
            </form>
        </div>
    </div>
   <script>
 
   document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("loginForm").addEventListener("submit", async function (event) {
        event.preventDefault(); // Prevent default form submission

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        try {
            const response = await fetch("http://localhost:8080/MegaCity_Cab_Service/auth", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: new URLSearchParams({ email, password }).toString(), // Encode data
            });

            const data = await response.json();

            if (response.ok) {
                window.location.href = "admin-dashboard.jsp"; // Redirect if successful
            } else {
                showPopup(data.message); // Show error message
            }
        } catch (error) {
            console.error("Error:", error);
            showPopup("An error occurred. Please try again.");
        }
    });

    function showPopup(message) {
        alert(message); // Replace with a custom modal if needed
    }
});


   </script>
</body>
</html>
