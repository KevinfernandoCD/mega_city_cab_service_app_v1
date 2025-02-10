<h1>Update Payment Transaction</h1>
<form class="payment-form" action="update-transaction.jsp" method="post">
    <div class="form-group">
        <label for="bill_id">Bill ID</label>
        <input type="text" id="bill_id" name="bill_id" readonly required>
    </div>

    <div class="form-group">
        <label for="amount">Amount</label>
        <input type="number" id="amount" name="amount" min="1" step="0.01" required>
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
