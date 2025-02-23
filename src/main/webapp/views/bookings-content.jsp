<h1>Bookings Management</h1>
<div class="table-container">
    <a href="add-booking.jsp" class="register-btn"> + New Booking</a>
    <table>
        <thead>
            <tr>
                <th>Booking ID</th>
                <th>Customer Name</th>
                <th>Driver Name</th>
                <th>Vehicle</th>
                <th>Pickup Location</th>
                <th>Drop-off Location</th>
                <th>Trip Distance</th>
                <th>Booking Date</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="bookingTableBody">
            <!-- Booking data will be inserted here dynamically -->
        </tbody>
    </table>
</div>

<script>
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";
    
     function updateBookingStatus(bookingId, newStatus) {
    if (confirm("Are you sure you want to update booking ID " + bookingId + " to " + newStatus + "?")) {
        fetch(API_BASE_URL + "/booking?id=" + bookingId + "&status=" + newStatus, {
            method: "PUT",
        })
        .then(response => {
            if (response.ok) {
                alert("Booking status updated to " + newStatus + ".");
                loadBookingTable(); // Reload the table to reflect the changes
            } else {
                return response.text().then(errorMessage => {
                    alert("Error updating booking status: " + errorMessage);
                    console.error("Update failed:", response.status, errorMessage);
                });
            }
        })
        .catch(error => {
            alert("Error updating booking status: " + error.message);
            console.error("Error during update fetch:", error);
        });
    }
}


    document.addEventListener("DOMContentLoaded", function() {
        loadBookingTable(); // Load table on page load
    });

  function loadBookingTable() {
    fetch(API_BASE_URL + "/booking")
        .then(response => response.text())
        .then(data => {
            const tableBody = document.getElementById("bookingTableBody");
            tableBody.innerHTML = ""; // Clear existing rows

            const bookingLines = data.trim().split("\n");

            bookingLines.forEach(line => {
                const matches = line.match(/BookingResponseDto\{id=(\d+), customerName='([^']+)', driverName='([^']+)', vehicleRegNo='([^']+)', pickupLocation='([^']+)', dropLocation='([^']+)', bookingDate=([^,]+), status='([^']+)', distance='([\d.]+)'\}/);

                if (matches) {
                    const [_, id, customerName, driverName, vehicleRegNo, pickupLocation, dropLocation, bookingDate, status, distance] = matches;

                    let statusButton = "";
                    let cancelButton = "";
                    let generateBillButton = ""; // Default: Hidden

                    if (status === "PENDING") {
                        statusButton = "<button class=\"edit-btn\" onclick=\"updateBookingStatus(" + id + ", 'ONGOING')\">ONGOING</button>";
                        cancelButton = "<button class=\"delete-btn\" onclick=\"updateBookingStatus(" + id + ", 'CANCELED')\">" +
                            "<i class=\"fa-solid fa-square-xmark\"></i></button>";
                    } else if (status === "ONGOING") {
                        statusButton = "<button class=\"edit-btn\" onclick=\"updateBookingStatus(" + id + ", 'COMPLETE')\">COMPLETE</button>";
                        cancelButton = "<button class=\"delete-btn\" onclick=\"updateBookingStatus(" + id + ", 'CANCELED')\">" +
                            "<i class=\"fa-solid fa-square-xmark\"></i></button>";
                    } else if (status === "COMPLETE") {
                        generateBillButton = "<a href=\"generate-bill.jsp?id=" + id + "\" class=\"bill-btn\">" +
                            "<i>Generate Bill</i></a>"; // Only show if status is "COMPLETE"
                    }

                    // Determine action buttons
                    let actionButtons = statusButton + cancelButton;
                    if (status === "COMPLETE") {
                        actionButtons = generateBillButton; // Only show bill button if "COMPLETE"
                    }

                    const row = document.createElement("tr");
                    row.innerHTML =
                        "<td>" + id + "</td>" +
                        "<td>" + customerName + "</td>" +
                        "<td>" + driverName + "</td>" +
                        "<td>" + vehicleRegNo + "</td>" +
                        "<td>" + pickupLocation + "</td>" +
                        "<td>" + dropLocation + "</td>" +
                        "<td>" + distance + " KM</td>" + // Added KM suffix
                        "<td>" + new Date(bookingDate).toLocaleString() + "</td>" +
                        "<td>" + status + "</td>" +
                        "<td>" + actionButtons + "</td>";

                    tableBody.appendChild(row);
                }
            });
        })
        .catch(error => console.error("Error fetching bookings:", error));
}



    function deleteBooking(bookingId) {
        if (confirm("Are you sure you want to delete booking with ID: " + bookingId + "?")) {
            fetch(API_BASE_URL + "/booking?id=" + bookingId, {
                method: 'DELETE',
            })
            .then(response => {
                if (response.ok) {
                    alert("Booking deleted successfully.");
                    loadBookingTable(); // Reload the booking table
                } else {
                    return response.text().then(errorMessage => {
                        alert("Error deleting booking: " + errorMessage);
                        console.error("Deletion failed:", response.status, errorMessage);
                    });
                }
            })
            .catch(error => {
                alert("Error deleting booking: " + error.message);
                console.error("Error during deletion fetch:", error);
            });
        }
    }
    
   
   
</script>
