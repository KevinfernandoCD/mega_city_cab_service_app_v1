
    <aside class="sidebar">
        <div class="logo" onclick="redirectToDashboard()">
                 <img src="../assets/logo.jpg" alt="Admin Portal">
                <p>Megacity Cabs</p>
            </div>
            <div class="user-info">
               
                <div class="user-details">
                    <p class="username"><%= username %></p>
                    <p class="email"><%= email %></p>
                </div>
            </div>
            <nav>
                <ul>
                    <li><a href="drivers.jsp"><i class="fas fa-person"></i> Manage Drivers</a></li>
                     <li><a href="vehicles.jsp"><i class="fas fa-car"></i> Manage Vehicles</a></li>
                    <li><a href="customers.jsp"><i class="fas fa-users"></i> Customers</a></li>
                    <li><a href="bookings.jsp"><i class="fas fa-book"></i> Manage Bookings</a></li>
                    <li><a href="billings.jsp"><i class="fas fa-file-invoice"></i> View Bills</a></li>
                    <li><a href="transactions.jsp"><i class="fas fa-exchange-alt"></i> View Transactions</a></li>
                    <li><a href="user-guide.jsp"><i class="fa-solid fa-question"></i>User Guild</a></li>
                    <li><a onclick="logoutUser()"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
                </ul>
            </nav>
        </aside>

 <script>
    function redirectToDashboard() {
        window.location.href = "admin-dashboard.jsp";
    }
    
    function logoutUser() {
    fetch("http://localhost:8080/MegaCity_Cab_Service/auth", {
        method: "DELETE",
        headers: { "Content-Type": "application/json" }
    })
    .then(response => response.json())
    .then(data => {
        alert(data.message); // Show success message
        window.location.href = "login.jsp"; // Redirect to login page
    })
    .catch(error => console.error("Error:", error));
}

</script>