<h1>Vehicle Management</h1>
<div class="table-container">
    <a href="register-vehicle.jsp" class="register-btn">Register New Vehicle</a>
    <table>
        <thead>
            <tr>
                <th>Vehicle ID</th>
                <th>Type</th>
                <th>Model</th>
                <th>Brand</th>
                <th>Capacity</th>
                <th>Color</th>
                <th>License Plate</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="vehicleTableBody">
        </tbody>
    </table>
</div>

<script>
   const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    document.addEventListener("DOMContentLoaded", function() {
        loadVehicleTable(); // Load table on page load
    });

    function loadVehicleTable() {
    fetch(API_BASE_URL + "/vehicle")
        .then(response => response.text())
        .then(data => {
            const tableBody = document.getElementById("vehicleTableBody");
            tableBody.innerHTML = ""; // Clear existing rows

            const vehicleLines = data.trim().split("\n");

            vehicleLines.forEach(line => {
                const matches = line.match(/Vehicle\{vehicle_ID='(\d+)', model='([^']+)', brand='([^']+)', color='([^']+)', registrationNumber='([^']+)', seatingCapacity=(\d+), availability=(true|false), type='([^']+)'\}/);

                if (matches) {
                    const [_, vehicle_ID, model, brand, color, registrationNumber, seatingCapacity, availability, type] = matches;
                    const isChecked = availability === "true" ? "checked" : "";

                    const row = document.createElement("tr");
                    row.innerHTML =
                        "<td>" + vehicle_ID + "</td>" +
                        "<td>" + type + "</td>" +
                        "<td>" + model + "</td>" +
                        "<td>" + brand + "</td>" +
                        "<td>" + seatingCapacity + "</td>" +
                        "<td>" + color + "</td>" +
                        "<td>" + registrationNumber + "</td>" +
                        "<td>" +
                        '<input type="checkbox" class="availability-toggle" data-id="' + vehicle_ID + '" ' + isChecked + ">" +
                        "</td>" +
                        "<td>" +
                        '<button class="delete-btn" onclick="deleteVehicle(\'' + vehicle_ID + '\')">' +
                        '<i class="fas fa-trash"></i></button>' +
                        "</td>";

                    tableBody.appendChild(row);
                }
            });

            // Add event listeners to all checkboxes
            document.querySelectorAll(".availability-toggle").forEach(checkbox => {
                checkbox.addEventListener("change", function () {
                    updateAvailability(this.dataset.id, this.checked);
                });
            });
        })
        .catch(error => console.error("Error fetching vehicles:", error));
}


   function updateAvailability(vehicleId, isAvailable) {
    fetch(API_BASE_URL + "/vehicle?id=" + vehicleId + "&availability=" + isAvailable, {
        method: "PUT"
    })
    .then(response => response.text()) // Read response as text
    .then(message => {
        alert(message); // Show success or failure message from servlet
        console.log("Update response:", message);
    })
    .catch(error => {
        alert("Error updating availability: " + error.message);
        console.error("Error during update fetch:", error);
    });
}

    function deleteVehicle(vehicleId) {
        if (confirm("Are you sure you want to delete vehicle with ID: " + vehicleId + "?")) {
            fetch(API_BASE_URL + "/vehicle?id=" + vehicleId, {
                method: "DELETE",
            })
            .then(response => {
                if (response.ok) {
                    alert("Vehicle deleted successfully.");
                    loadVehicleTable(); // Reload the table
                } else {
                    return response.text().then(errorMessage => {
                        alert("Error deleting vehicle: " + errorMessage);
                        console.error("Deletion failed:", response.status, errorMessage);
                    });
                }
            })
            .catch(error => {
                alert("Error deleting vehicle: " + error.message);
                console.error("Error during deletion fetch:", error);
            });
        }
    }
</script>
