<h1>Register New Customer</h1>
<form class="register-form" id="customerForm">
    <div class="form-group">
        <label for="name">Full Name</label>
        <input type="text" id="name" name="name" required>
    </div>

    <div class="form-group">
        <label for="nic">NIC No</label>
        <input type="text" id="nic" name="nic" required>
    </div>

    <div class="form-group">
        <label for="phone">Phone Number</label>
        <input type="tel" id="phone" name="phone" required>
    </div>

    <div class="form-group">
        <label for="address">Address</label>
        <input type="text" id="address" name="address" required>
    </div>

    <div class="form-buttons">
        <button type="button" class="back-btn" onclick="history.back()">Back</button>
        <button type="submit" class="create-btn">Register</button>
    </div>
</form>

<script>
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    document.getElementById("customerForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Prevent default form submission

        const formData = new URLSearchParams();
        formData.append("name", document.getElementById("name").value);
        formData.append("nic", document.getElementById("nic").value); // Corrected ID
        formData.append("phone", document.getElementById("phone").value);
        formData.append("address", document.getElementById("address").value);

        try {
            const response = await fetch(API_BASE_URL + "/customer", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: formData
            });

            const result = await response.text();
            if (response.ok) {
                alert("Customer has been registered successfully!");
                document.getElementById("customerForm").reset();
            } else {
                alert("Error: " + result || "Registration failed");
            }
        } catch (error) {
            alert("An error occurred: " + error.message);
        }
    });
</script>
