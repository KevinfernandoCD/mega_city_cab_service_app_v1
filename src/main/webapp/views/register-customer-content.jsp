

<h1>Register New Customer</h1>
<form class="register-form" action="submit-customer.jsp" method="post">
    <div class="form-group">
        <label for="name">Full Name</label>
        <input type="text" id="name" name="name" required>
    </div>

    <div class="form-group">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>
    </div>

    <div class="form-group">
        <label for="phone">Phone Number</label>
        <input type="tel" id="phone" name="phone" required>
    </div>

    <div class="form-group">
        <label for="address">Address</label>
        <input type="text" id="address" name="address" required>
    </div>

    <div class="form-buttons">
        <button type="button" class="back-btn" onclick="history.back()">Back</button>
        <button type="submit" class="create-btn">Register</button>
    </div>
</form>
