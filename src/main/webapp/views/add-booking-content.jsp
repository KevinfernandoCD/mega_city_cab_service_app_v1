<h1>Add New Booking</h1>
<form class="register-form" id="bookingForm">
     <div class="form-group">
        <label for="type">Select a Customer</label>
        <select id="customerSelect" name="customer_id" required>
        
        </select>
    </div>
    
     <div class="form-group">
          <label for="type">Select a Driver</label>
        <select  id="driverSelect" name="driver_id" required>
        
        </select>
    </div>
   

      <div class="form-group">
          
        <label for="type">Select a Vehicle</label>
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
            <label for="distance">Trip Distance (KM)</label>
            <input type="number" id="distance" name="distance" required>
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

async function loadCustomers() {
    try {
        const response = await fetch(API_BASE_URL + '/customer'); 
        if (!response.ok) {
            throw new Error('Failed to fetch customers');
        }
        const data = await response.text();
        
        const customerSelect = document.getElementById('customerSelect');
        
        const customerLines = data.split('\n'); // Split response lines
        
        customerLines.forEach(line => {
            const match = line.match(/Customer\{id=(\d+), name='([^']+)'/);
            if (match) {
                const option = document.createElement('option');
                option.value = match[1];
                option.textContent = match[2];
                customerSelect.appendChild(option);
            }
        });
    } catch (error) {
        console.error('Error loading customers:', error);
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

document.getElementById("bookingForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent default form submission

    const formData = new URLSearchParams();
    formData.append("customer_id", document.getElementById("customerSelect").value);
    formData.append("driver_id", document.getElementById("driverSelect").value);
    formData.append("vehicle_id", document.getElementById("vehicleSelect").value);
    formData.append("pickup_location", document.getElementById("pickupLocation").value);
    formData.append("drop_location", document.getElementById("dropoffLocation").value);
    formData.append("distance", document.getElementById("distance").value);

    try {
        const response = await fetch(API_BASE_URL + "/booking", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData
        });

        const result = await response.text();
        if (response.ok) {
            alert("Booking has been created successfully!");
            document.getElementById("bookingForm").reset();
        } else {
            alert("Error: " + result.toString() || "Booking failed");
        }
    } catch (error) {
        alert("An error occurred: " + error.message);
    }
});

</script>

