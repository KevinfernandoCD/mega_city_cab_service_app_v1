<h1>Customer Management</h1>
<div class="table-container">
    <a href="register-customer.jsp" class="register-btn">Register New Customer</a>
    <table>
        <thead>
            <tr>
                <th>Reg No</th>
                <th>Name</th>
                <th>NIC</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="customerTableBody">
        </tbody>
    </table>
</div>

<script>
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    document.addEventListener("DOMContentLoaded", function() {
        loadCustomerTable(); // Load table on page load
    });

    function loadCustomerTable() {
        fetch(API_BASE_URL + "/customer")
            .then(response => response.text())
            .then(data => {
                console.log("DATA " , data)
                const tableBody = document.getElementById("customerTableBody");
                tableBody.innerHTML = "";  // Clear existing rows

                const customerLines = data.trim().split("\n");

                customerLines.forEach(line => {
                    const matches = line.match(/Customer\{id=(\d+), name='([^']+)', nic='([^']+)', phone='([^']+)', address='([^']+)'\}/);

                    if (matches) {
                        console.log("MATCHED")
                        
                        const [_, id, name, nic, phone, address] = matches;

                        const row = document.createElement("tr");
                        row.innerHTML =
    "<td>" + id + "</td>" +
    "<td>" + name + "</td>" +
    "<td>" + nic + "</td>" +
    "<td>" + phone + "</td>" +
    "<td>" + address + "</td>" +
    "<td>" +
    "<a href=\"update-customer.jsp?id=" + id + "\" class=\"edit-btn\">" +
    "<i class=\"fas fa-edit\"></i></a>" +
    "<button class=\"delete-btn\" onclick=\"deleteCustomer('" + id + "')\">" +
    "<i class=\"fas fa-trash\"></i></button>" +
    "</td>";

                        tableBody.appendChild(row);
                    }
                });
            })
            .catch(error => console.error("Error fetching customers:", error));
    }

    function deleteCustomer(customerId) {
        if (confirm("Are you sure you want to delete customer with ID: " + customerId + "?")) {
            fetch(API_BASE_URL + "/customers?customerId=" + customerId, {
                method: 'DELETE',
            })
            .then(response => {
                if (response.ok) {
                    alert("Customer deleted successfully.");
                    loadCustomerTable(); // Reload the customer table
                } else {
                    return response.text().then(errorMessage => {
                        alert("Error deleting customer: " + errorMessage);
                        console.error("Deletion failed:", response.status, errorMessage);
                    });
                }
            })
            .catch(error => {
                alert("Error deleting customer: " + error.message);
                console.error("Error during deletion fetch:", error);
            });
        }
    }
</script>
