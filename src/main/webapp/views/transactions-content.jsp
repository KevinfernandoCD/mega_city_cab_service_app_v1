<h1>Transactions Management</h1>
<div class="table-container">
    <table>
        <thead>
            <tr>
                <th>Transaction ID</th>
                <th>Bill ID</th>
                <th>Payment Method</th>
                <th>Amount</th>
                <th>Customer ID</th>
                <th>Date</th>
                            <th>Actions</th>
            </tr>
        </thead>
        <tbody id="transactionTableBody">
        </tbody>
    </table>
</div>

<script>
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    document.addEventListener("DOMContentLoaded", function() {
        loadTransactionTable(); // Load table on page load
    });

    function loadTransactionTable() {
        fetch(API_BASE_URL + "/transaction")
            .then(response => response.text())
            .then(data => {
                console.log("Transaction Data:", data);
                const tableBody = document.getElementById("transactionTableBody");
                tableBody.innerHTML = "";

                const transactionLines = data.trim().split("\n");

                transactionLines.forEach(line => {
                    const matches = line.match(/Transaction\{id=(\d+), billId=(\d+), customerId=(\d+), bookingId=(\d+), amount=([\d.]+), paymentMethod='([^']+)', date='([\d-T:]+)'\}/);

                    if (matches) {
                        const id = matches[1];
                        const billId = matches[2];
                        const customerId = matches[3];
                        const bookingId = matches[4];
                        const amount = parseFloat(matches[5]).toFixed(2);
                        const paymentMethod = matches[6];
                        const date = new Date(matches[7]).toLocaleString();

                        const row = document.createElement("tr");
                        row.innerHTML =
                            row.innerHTML =
    "<td>" + id + "</td>" +
    "<td>" + billId + "</td>" +
    "<td>" + paymentMethod + "</td>" +
    "<td>Rs." + amount + "</td>" +
    "<td>" + customerId + "</td>" +
    "<td>" + date + "</td>" +
    "<td>" +
    "<a href=\"#\" class=\"edit-btn\">" + 
    "<i>View Details</i></a>" +
    "</td>";


                        tableBody.appendChild(row);
                    }
                });
            })
            .catch(error => console.error("Error fetching transactions:", error));
    }
</script>
