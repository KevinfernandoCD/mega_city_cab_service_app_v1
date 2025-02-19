<h1>Register New Vehicle</h1>
<form class="register-form" id="vehicleForm">
    <div class="form-group">
        <label for="model">Vehicle Model</label>
        <input type="text" id="model" name="model" required>
    </div>

    <div class="form-group">
        <label for="brand">Brand</label>
        <input type="text" id="brand" name="brand" required>
    </div>

    <div class="form-group">
        <label for="regNo">Registration Number</label>
        <input type="text" id="regNo" name="regNo" required>
    </div>

    <div class="form-group">
        <label for="color">Color</label>
        <input type="text" id="color" name="color" required>
    </div>

    <div class="form-group">
        <label for="capacity">Seating Capacity</label>
        <input type="number" id="capacity" name="capacity" min="1" required>
    </div>

    <div class="form-group">
        <label for="type">Vehicle Type</label>
        <select id="type" name="type" required>
            <option value="Car">Car</option>
            <option value="Van">Van</option>
        </select>
    </div>

    <div class="form-buttons">
        <button type="button" class="back-btn" onclick="history.back()">Back</button>
        <button type="submit" class="create-btn">Register</button>
    </div>
</form>

<script>
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    document.getElementById("vehicleForm").addEventListener("submit", async function(event) {
        event.preventDefault(); // Prevent default form submission

        const formData = new URLSearchParams();
        formData.append("model", document.getElementById("model").value);
        formData.append("brand", document.getElementById("brand").value);
        formData.append("regNo", document.getElementById("regNo").value);
        formData.append("color", document.getElementById("color").value);
        formData.append("capacity", document.getElementById("capacity").value);
        formData.append("type", document.getElementById("type").value);

        try {
            const response = await fetch(API_BASE_URL + "/vehicle", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: formData
            });

            const result = await response.text();
            if (response.ok) {
                alert("Vehicle has been registered successfully!");
                document.getElementById("vehicleForm").reset();
            } else {
                alert("Error: " + result.toString() || "Registration failed");
            }
        } catch (error) {
            alert("An error occurred: " + error.message);
        }
    });
</script>
