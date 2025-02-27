<h1>Welcome, ${username}</h1>
<hr />
<div class="stats">
    <div class="stat-item">
        <img src="../assets/Customer.jpg" alt="Customers" />
        <h3>Total Registered Customers</h3>
        <p id="customerCount">0</p>
    </div>
    <div class="stat-item">
        <img src="../assets/ongoing.jpg" alt="Bookings" />
        <h3>Ongoing Bookings/Rides</h3>
        <p id="bookingCount">0</p>
    </div>
    <div class="stat-item">
        <img src="../assets/Drivers.jpg" alt="Drivers" />
        <h3>Total Registered Drivers</h3>
        <p id="driverCount">0</p>
    </div>
</div>

<script>
const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

async function loadDrivers() {
    try {
        const response = await fetch(API_BASE_URL + "/drivers?action=available");
        if (!response.ok) throw new Error("Failed to fetch drivers");

        const data = await response.text();
        const driverCount = data.split("\n").filter(line => line.includes("Driver")).length || 0;

        document.getElementById("driverCount").textContent = driverCount;
    } catch (error) {
        console.error("Error loading drivers:", error);
    }
}

async function loadCustomers() {
    try {
        const response = await fetch(API_BASE_URL + "/customer");
        if (!response.ok) throw new Error("Failed to fetch customers");

        const data = await response.text();
        const customerCount = data.split("\n").filter(line => line.includes("Customer")).length || 0;

        document.getElementById("customerCount").textContent = customerCount;
    } catch (error) {
        console.error("Error loading customers:", error);
    }
}
async function loadBookings() {
    try {
        const response = await fetch(API_BASE_URL + "/booking?action=list");
        if (!response.ok) throw new Error("Failed to fetch bookings");

        const data = await response.text();
        
        const ongoingBookings = data.split("\n").filter(line => line.includes("status='ONGOING'")).length || 0;

        document.getElementById("bookingCount").textContent = ongoingBookings;
    } catch (error) {
        console.error("Error loading bookings:", error);
    }
}

document.addEventListener("DOMContentLoaded", () => {
    loadDrivers();
    loadCustomers();
    loadBookings();
});
</script>
