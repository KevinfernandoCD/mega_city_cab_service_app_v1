<h1>Update Booking Details</h1>
<form class="register-form" id="vehicleForm">
   
   <div class="form-group">
        <label for="type">Assign a Driver</label>
        <select id="driverSelect" name="driver_id" required>
  
        </select>
    </div>
      <div class="form-group">
          
        <label for="type">Assign a Vehicle</label>
        <select id="vehicleSelect" name="vehicle_id" required>
      
        </select>
    </div>

      <div class="form-group">
        <label for="type">Pickup Location</label>
        <select id="pickupLocation" name="pickup_location" required>
  
        </select>
    </div>
     <div class="form-group">
        <label for="type">Drop-Off Location</label>
        <select id="dropoffLocation" name="drop_location" required>
       
        </select>
    </div>

    <div class="form-group">
        <label for="type">Booking Status</label>
        <select id="status" name="status" required>
            <option value="PENDINNG">Pending</option>
            <option value="ONGOING">Ongoing</option>
            <option value="COMPLETE">Complete</option>
             <option value="CANCELLED">Cancelled</option>
        </select>
    </div>

    <div class="form-buttons">
        <button type="button" class="back-btn" onclick="history.back()">Back</button>
        <button type="submit" class="create-btn">Add New Booking</button>
    </div>
</form>

<script>
    
    const sriLankanCities = [
    "Colombo", "Kandy", "Galle", "Jaffna", "Negombo", 
    "Anuradhapura", "Polonnaruwa", "Trincomalee", "Batticaloa", "Ratnapura",
    "Kurunegala", "Nuwara Eliya", "Matara", "Badulla", "Puttalam"
];
    
     const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>"
 async function loadDrivers() {
    try {
        const response = await fetch(API_BASE_URL + '/drivers?action=available'); 
        if (!response.ok) {
            throw new Error('Failed to fetch drivers');
        }
        const data = await response.text();
        
        const driverSelect = document.getElementById('driverSelect');
        
        const driverLines = data.split('\n'); // Split response lines
        
        driverLines.forEach(line => {
            const match = line.match(/Driver\{id=(\d+), username='([^']+)'/); // Extract ID and username
            if (match) {
                const option = document.createElement('option');
                option.value = match[1]; // ID as value
                option.textContent = match[2]; // Username as display
                driverSelect.appendChild(option);
            }
        });
    } catch (error) {
        console.error('Error loading drivers:', error);
    }
}


async function loadVehicles() {
    try {
        const response = await fetch(API_BASE_URL + '/vehicle?action=available'); 
        if (!response.ok) {
            throw new Error('Failed to fetch vehicles');
        }
        const data = await response.text();
        
        const vehicleSelect = document.getElementById('vehicleSelect');
        
        const vehicleLines = data.split('\n');
        
        vehicleLines.forEach(line => {
                const match = line.match(/Vehicle\{vehicle_ID='(\d+)', model='([^']+)', brand='([^']+)', .*? registrationNumber='([^']+)'/);

            if (match) {
                const option = document.createElement('option');
                option.value = match[1]; // ID as value
                option.textContent = match[3] +" "+ match[2]; 
                vehicleSelect.appendChild(option);
            }
        });
    } catch (error) {
        console.error('Error loading vehicles:', error);
    }
}


function populateLocationDropdown(selectId) {
    const selectElement = document.getElementById(selectId);
    
    sriLankanCities.forEach(city => {
        const option = document.createElement("option");
        option.value = city;
        option.textContent = city;
        selectElement.appendChild(option);
    });
}
// Call all functions on page load
document.addEventListener('DOMContentLoaded', () => {
    loadDrivers();
    loadCustomers();
    loadVehicles();
    populateLocationDropdown("pickupLocation");
    populateLocationDropdown("dropoffLocation");
});


</script>
