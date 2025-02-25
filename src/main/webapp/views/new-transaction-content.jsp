<h1>New Payment Transaction</h1>
<form class="payment-form" id="transaction-form">
    <div class="form-group">
        <label for="bill_id">Bill ID</label>
        <input type="text" id="bill_id" name="bill_id" readonly required>
    </div>

    <div class="form-group">
        <label for="amount">Amount</label>
        <input type="number" id="amount" name="amount" min="1" step="0.01" required>
    </div>

    <div class="form-group">
        <label for="customer">Paid By</label>
        <select id="customer" name="customer" required></select>
    </div>

    <div class="form-group">
        <label for="payment_method">Payment Method</label>
        <select id="payment_method" name="payment_method" required>
            <option value="Cash">Cash</option>
            <option value="Credit Card">Credit Card</option>
            <option value="Debit Card">Debit Card</option>
            <option value="Mobile Payment">Mobile Payment</option>
            <option value="Bank Transfer">Bank Transfer</option>
        </select>
    </div>

    <div class="form-buttons">
        <button type="button" class="back-btn" onclick="history.back()">Back</button>
        <button type="submit" class="update-btn">Update Payment</button>
    </div>
</form>

<script>
    const API_BASE_URL = "<%= application.getInitParameter("apiBaseUrl") %>";

    // Function to get URL parameters
    function getQueryParam(param) {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    }

    // Function to load customers from API
    async function loadCustomers() {
        try {
            const response = await fetch(API_BASE_URL+"/customer");
            if (!response.ok) {
                throw new Error('Failed to fetch customers');
            }
            const data = await response.text();
            const customerSelect = document.getElementById('customer');
            const customerLines = data.split('\n'); 

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

    // Function to submit the transaction form via URL-encoded data
    async function submitTransaction(event) {
        event.preventDefault();

        const billId = getQueryParam('billId');
        const bookingId = getQueryParam('bookingId');
        const amount = document.getElementById('amount').value;
        const customerId = document.getElementById('customer').value;
        const paymentMethod = document.getElementById('payment_method').value;

        if (!billId || !bookingId || !amount || !customerId || !paymentMethod) {
            alert("All fields are required!");
            return;
        }

        // Create URL-encoded form data
        const formData = new URLSearchParams();
        formData.append("bill_id", billId);
        formData.append("booking_id", bookingId);
        formData.append("amount", amount);
        formData.append("customer_id", customerId);
        formData.append("payment_method", paymentMethod);

        try {
            const response = await fetch(API_BASE_URL + "/transaction", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: formData.toString()
            });

            if (response.ok) {
                alert("Transaction updated successfully!");
                window.location.href = "transactions.jsp";
            } else {
                const errorText = await response.text();
                alert(`Failed to update transaction: ${errorText}`);
            }
        } catch (error) {
            console.error("Error submitting transaction:", error);
            alert("An error occurred while processing the transaction.");
        }
    }

    document.addEventListener('DOMContentLoaded', () => {
        const billId = getQueryParam('billId');
        if (billId) {
            document.getElementById('bill_id').value = billId;
        }

        loadCustomers();
        document.getElementById('transaction-form').addEventListener('submit', submitTransaction);
    });
</script>
