<h1>Update Driver</h1>
<form class="update-form" id="driverForm">
    <div class="form-grid">
      
        <div class="form-group">
            <label for="phone">Phone Number</label>
            <input type="number" id="phone" name="phone" required>
        </div>

        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" id="address" name="address" required>
        </div>

        <!-- Custom styled checkbox -->
        <div class="form-group checkbox-group">
            <label for="availability">Driver Availability</label>
            <label class="switch">
                <input type="checkbox" id="availability" name="availability">
                <span class="slider round"></span>
            </label>
        </div>
    </div>

    <div class="form-buttons">
        <button type="submit" class="create-btn">Update</button>
    </div>
</form>

<script>
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    // Get driver ID from URL query parameters
    const urlParams = new URLSearchParams(window.location.search);
    const driverId = urlParams.get("id");

    // Function to fetch driver details and populate the form
  async function fetchDriverDetails() {
    try {
        const response = await fetch(API_BASE_URL + "/drivers?driverId=" + driverId);
        if (!response.ok) throw new Error("Failed to fetch driver details");

        const driverText = await response.text(); // Get raw text response
        console.log("Driver Response:", driverText); // Debugging log

        // Extract values using Regular Expressions
        const phoneMatch = driverText.match(/phoneNo='([^']+)'/);
        const addressMatch = driverText.match(/address='([^']+)'/);
        const availabilityMatch = driverText.match(/isAvailable=(true|false)/);

        const phone = phoneMatch ? phoneMatch[1] : "";
        const address = addressMatch ? addressMatch[1] : "";
        const isAvailable = availabilityMatch ? availabilityMatch[1] === "true" : false;

        // Populate form fields
        document.getElementById("phone").value = phone;
        document.getElementById("address").value = address;
        document.getElementById("availability").checked = isAvailable;

    } catch (error) {
        console.error("Error fetching driver details:", error.message);
    }
}
    // Call fetchDriverDetails when the page loads
    document.addEventListener("DOMContentLoaded", fetchDriverDetails);

    document.getElementById("driverForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Prevent default form submission

        const phone = encodeURIComponent(document.getElementById("phone").value);
        const address = encodeURIComponent(document.getElementById("address").value);
        const isAvailable = document.getElementById("availability").checked ? "true" : "false";

        // Construct query parameters
        const queryParams = "driverId=" + driverId + "&phone=" + phone + "&address=" + address + "&available=" + isAvailable;

        try {
            const response = await fetch(API_BASE_URL + "/drivers?" + queryParams, {
                method: "PUT"
            });

            const result = await response.text();
            if (response.ok) {
                alert("Driver has been updated successfully!");
                document.getElementById("driverForm").reset();
            } else {
                alert("Error: " + result.toString() || "Failed to update driver!");
            }
        } catch (error) {
            alert("An error occurred: " + error.message);
        }
    });
</script>
