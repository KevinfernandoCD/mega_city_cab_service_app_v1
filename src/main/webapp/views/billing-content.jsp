<h1>Bills Management</h1>
<div class="table-container">
    <table>
        <thead>
            <tr>
                <th>Bill ID</th>
                <th>Booking ID</th>
                <th>Base Fare</th>
                <th>Total Trip Fare</th>
                <th>Added Discount</th>
                <th>Tax Amount</th>
                <th>Paid</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody id="billingTableBody">
            <!-- Billing data will be inserted dynamically -->
        </tbody>
    </table>
</div>

<script>
    
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    document.addEventListener("DOMContentLoaded", function () {
        loadBillingTable(); // Load billing table on page load
    });

    function loadBillingTable() {
        fetch(API_BASE_URL + "/billing")
            .then(response => response.text())
            .then(data => {
                const tableBody = document.getElementById("billingTableBody");
                tableBody.innerHTML = ""; 

                const billingLines = data.trim().split("\n");

                billingLines.forEach(line => {
                    const matches = line.match(/Billing\{id=(\d+), totalFare=([\d.]+), baseFare=([\d.]+), addedDiscountAmount=([\d.]+), addedTaxAmount=([\d.]+), paid=(true|false), bookingId=(\d+)\}/);

                    if (matches) {
                        
                        const [_, billId, totalFare, baseFare, discount, tax, paid, bookingId] = matches;

                        const row = document.createElement("tr");
                      row.innerHTML =
    "<td>" + billId + "</td>" +
    "<td>" + bookingId + "</td>" +
    "<td>Rs." + parseFloat(baseFare).toFixed(2) + "</td>" +
    "<td>Rs." + parseFloat(totalFare).toFixed(2) + "</td>" +
    "<td>Rs." + parseFloat(discount).toFixed(2) + "</td>" +
    "<td>Rs." + parseFloat(tax).toFixed(2) + "</td>" +
    "<td>" + (paid === "true" ? "Yes" : "No") + "</td>" +
    "<td>" +
    "<a href=\"new-transaction.jsp?billId=" + billId + "\" class=\"edit-btn\">" +
    "<i>Update Payment</i></a>" +
    "</td>";


                        tableBody.appendChild(row);
                    }
                });
            })
            .catch(error => console.error("Error fetching billing data:", error));
    }
</script>
