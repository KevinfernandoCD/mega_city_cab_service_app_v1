<h1>Register New Driver</h1>
<form class="register-form" id="driverForm">
    <div class="form-grid">
        <div class="form-group">
            <label for="name">Full Name</label>
            <input type="text" id="name" name="name" required>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>
        
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="phone">Phone Number</label>
            <input type="number" id="phone" name="phone" required>
        </div>

        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" id="address" name="address" required>
        </div>
    </div>

    <div class="form-buttons">
        <button type="button" class="back-btn" onclick="history.back()">Back</button>
        <button type="submit" class="create-btn">Register</button>
    </div>
</form>

<script>
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    document.getElementById("driverForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Prevent default form submission

        const formData = new URLSearchParams();
        formData.append("username", document.getElementById("name").value);
        formData.append("email", document.getElementById("email").value);
        formData.append("password", document.getElementById("password").value);
        formData.append("phone", document.getElementById("phone").value);
        formData.append("address", document.getElementById("address").value);

        try {
            const response = await fetch(API_BASE_URL + "/drivers", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: formData
            });

            const result = await response.text();
            if (response.ok) {
                alert("Driver has been registered successfully!");
                document.getElementById("driverForm").reset();
            } else {
                alert("Error: " + result.toString() || "Registration failed");
            }
        } catch (error) {
            
            alert("An error occurred: " + error.message);
        }
    });
</script>

