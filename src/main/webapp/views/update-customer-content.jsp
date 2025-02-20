<h1>Update Customer</h1>
<form class="update-form" id="customerForm">
    <div class="form-grid">
        
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="phone">Phone Number</label>
            <input type="number" id="phone" name="phone" required>
        </div>

        <div class="form-group">
            <label for="nic">NIC</label>
            <input type="text" id="nic" name="nic" required>
        </div>

        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" id="address" name="address" required>
        </div>

    </div>

    <div class="form-buttons">
        <button type="submit" class="create-btn">Update</button>
    </div>
</form>

<script>
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    // Get customer ID from URL query parameters
    const urlParams = new URLSearchParams(window.location.search);
    const customerId = urlParams.get("id");

    // Function to fetch customer details and populate the form
    async function fetchCustomerDetails() {
        try {
            const response = await fetch(API_BASE_URL + "/customer?id=" + customerId);
            if (!response.ok) throw new Error("Failed to fetch customer details");

            const customerText = await response.text(); // Get raw text response
            console.log("Customer Response:", customerText); // Debugging log

            // Extract values using Regular Expressions
            const idMatch = customerText.match(/id=(\d+)/);
            const nameMatch = customerText.match(/name='([^']+)'/);
            const nicMatch = customerText.match(/nic='([^']+)'/);
            const phoneMatch = customerText.match(/phone='([^']+)'/);
            const addressMatch = customerText.match(/address='([^']+)'/);

            const name = nameMatch ? nameMatch[1] : "";
            const nic = nicMatch ? nicMatch[1] : "";
            const phone = phoneMatch ? phoneMatch[1] : "";
            const address = addressMatch ? addressMatch[1] : "";

            // Populate form fields
            document.getElementById("name").value = name;
            document.getElementById("nic").value = nic;
            document.getElementById("phone").value = phone;
            document.getElementById("address").value = address;

        } catch (error) {
            console.error("Error fetching customer details:", error.message);
        }
    }

    // Call fetchCustomerDetails when the page loads
    document.addEventListener("DOMContentLoaded", fetchCustomerDetails);

    document.getElementById("customerForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Prevent default form submission

        const name = encodeURIComponent(document.getElementById("name").value);
        const nic = encodeURIComponent(document.getElementById("nic").value);
        const phone = encodeURIComponent(document.getElementById("phone").value);
        const address = encodeURIComponent(document.getElementById("address").value);

        // Construct query parameters
        const queryParams = "" + "id=" + customerId + "&name=" + name + "&nic=" + nic + "&phone=" + phone + "&address=" + address;

        try {
            const response = await fetch(API_BASE_URL + "/customer?" + queryParams, {
                method: "PUT"
            });

            const result = await response.text();
            if (response.ok) {
                alert("Customer has been updated successfully!");
                document.getElementById("customerForm").reset();
            } else {
                alert("Error: " + result.toString() || "Failed to update customer!");
            }
        } catch (error) {
            alert("An error occurred: " + error.message);
        }
    });
</script>
