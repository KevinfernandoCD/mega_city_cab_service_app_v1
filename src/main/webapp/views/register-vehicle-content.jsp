<h1>Register New Vehicle</h1>
<form class="register-form" action="submit-vehicle.jsp" method="post">
    <div class="form-group">
        <label for="vehicleNumber">Vehicle Number</label>
        <input type="text" id="vehicleNumber" name="vehicleNumber" required>
    </div>

    <div class="form-group">
        <label for="vehicleModel">Vehicle Model</label>
        <input type="text" id="vehicleModel" name="vehicleModel" required>
    </div>

    <div class="form-group">
        <label for="manufacturer">Manufacturer</label>
        <input type="text" id="manufacturer" name="manufacturer" required>
    </div>

    <div class="form-group">
        <label for="year">Year</label>
        <input type="number" id="year" name="year" min="1900" max="2025" required>
    </div>

    <div class="form-group">
        <label for="seatingCapacity">Seating Capacity</label>
        <input type="number" id="seatingCapacity" name="seatingCapacity" min="1" required>
    </div>

    <div class="form-group">
        <label for="vehicleType">Vehicle Type</label>
        <select id="vehicleType" name="vehicleType" required>
            <option value="Sedan">Sedan</option>
            <option value="SUV">SUV</option>
            <option value="Hatchback">Hatchback</option>
            <option value="Van">Van</option>
            <option value="Other">Other</option>
        </select>
    </div>

    <div class="form-buttons">
        <button type="button" class="back-btn" onclick="history.back()">Back</button>
        <button type="submit" class="create-btn">Register</button>
    </div>
</form>
