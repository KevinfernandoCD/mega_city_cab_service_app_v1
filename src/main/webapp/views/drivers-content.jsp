<h1>Driver Management</h1>
<div class="table-container">
    <a href="register-driver.jsp" class="register-btn">Register New Driver</a>
    <table>
        <thead>
            <tr>
                <th>Driver ID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Availability</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="driverTableBody">
            </tbody>
    </table>
</div>

<script>

    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    document.addEventListener("DOMContentLoaded", function() {
        loadDriverTable(); // Load table on page load
    });

    function loadDriverTable() {
        fetch(API_BASE_URL + "/drivers?action=all")
            .then(response => response.text())
            .then(data => {
                const tableBody = document.getElementById("driverTableBody");
                tableBody.innerHTML = "";  // Clear existing rows

                const driverLines = data.trim().split("\n");

                driverLines.forEach(line => {
                    const matches = line.match(/Driver\{id=(\d+), username='([^']+)', phoneNo='([^']+)', address='([^']+)', isAvailable=(true|false)\}/);

                    if (matches) {
                        const [_, id, username, phoneNo, address, isAvailable] = matches;

                        const row = document.createElement("tr");
                        row.innerHTML =
                            "<td>" + id + "</td>" +
                            "<td>" + username + "</td>" +
                            "<td>" + phoneNo + "</td>" +
                            "<td>" + address + "</td>" +
                            "<td>" + (isAvailable === "true" ? "Available" : "Not Available") + "</td>" +
                            "<td>" +
                            '<a href="update-driver.jsp?id=' + id + '" class="edit-btn">' +
                            '<i class="fas fa-edit"></i></a>' +
                            '<button class="delete-btn" onclick="deleteDriver(\'' + id + '\')">' + // Call JS delete function
                            '<i class="fas fa-trash"></i></button>' +
                            "</td>";

                        tableBody.appendChild(row);
                    }
                });
            })
            .catch(error => console.error("Error fetching drivers:", error));
    }


    function deleteDriver(driverId) {
        if (confirm("Are you sure you want to delete driver with ID: " + driverId + "?")) {
            fetch(API_BASE_URL + "/drivers?driverId=" + driverId, {
                method: 'DELETE',
            })
            .then(response => {
                if (response.ok) {
                    alert("Driver deleted successfully.");
                    loadDriverTable(); // Reload the driver table
                } else {
                    return response.text().then(errorMessage => {
                        alert("Error deleting driver: " + errorMessage);
                        console.error("Deletion failed:", response.status, errorMessage);
                    });
                }
            })
            .catch(error => {
                alert("Error deleting driver: " + error.message);
                console.error("Error during deletion fetch:", error);
            });
        }
    }
</script>